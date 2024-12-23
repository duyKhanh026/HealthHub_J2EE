package com.healthhub.hospital.controller.User;

import com.healthhub.hospital.Entity.*;
import com.healthhub.hospital.controller.EmailController;
import com.healthhub.hospital.service.*;
import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DatLichKhamController {

    @Value("${google.recaptcha.key}")
    private String recaptchaKey;
	
    private LichKhamService lichKhamService;

    private TaiKhoanService taiKhoanService;

    private ChiTietLichKhamService chiTietLichKhamService;

    private ThanhToanService thanhToanService;


    private BenhNhan benhNhan = new BenhNhan();

    private ChiTietLichKham chiTietLichKham = new ChiTietLichKham();

    private ThanhToan thanhToan = new ThanhToan();

    private EmailController emailController;

    @Autowired
    private PageCustomIndexService pageCustomIndexService;
    @Autowired
    private PageCustomIndex_1Service pageCustomIndex_1Service;

    public DatLichKhamController(LichKhamService lichKhamService, ChiTietLichKhamService chiTietLichKhamService,
                                 TaiKhoanService taiKhoanService, ThanhToanService thanhToanService, EmailController emailController) {
        this.lichKhamService = lichKhamService;
        this.chiTietLichKhamService = chiTietLichKhamService;
        this.taiKhoanService = taiKhoanService;
        this.thanhToanService = thanhToanService;
        this.emailController = emailController;

    }

    @Autowired
    private BenhNhanService benhnhanService;

    @GetMapping({ "/DatLichKham" })
    public String datlichKham(Model model, Authentication authentication) {
        BenhNhan benhNhan1 = null;

        // Kiểm tra xem người dùng đang đăng nhập bằng tài khoản Google hay tài khoản mật khẩu thông thường
        if (authentication.getPrincipal() instanceof OAuth2User) {
            // Nếu đăng nhập bằng Google, lấy thông tin từ OAuth2User
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            String email = oauth2User.getAttribute("email");

            // Tìm bệnh nhân theo email từ Google
            benhNhan1 = benhnhanService.findByEmail(email);
        } else {
            // Nếu đăng nhập bằng tài khoản mật khẩu thông thường
            TaiKhoan tk = taiKhoanService.findByTenDN(authentication.getName());
            if (tk != null && tk.getBenhNhan() != null) {
                benhNhan1 = benhnhanService.getBenhNhanById(tk.getBenhNhan().getMaBN());
            }
        }

        // Nếu tìm thấy bệnh nhân, thiết lập thông tin vào đối tượng LichKham
        if (benhNhan1 != null) {
            LichKham lichKham = new LichKham();
            lichKham.setHoten(benhNhan1.getHoTen());
            lichKham.setEmail(benhNhan1.getEmail());
            lichKham.setSDT(benhNhan1.getSDT());

            // Thêm đối tượng LichKham và recaptchaKey vào model
            model.addAttribute("lichKham", lichKham);
        }

        model.addAttribute("recaptchaKey", recaptchaKey);
        PageCustomIndex page = pageCustomIndexService.getFirstPageCustomIndex();
        List<PageCustomIndex_1> page_1 = pageCustomIndex_1Service.findAll();

        model.addAttribute("page", page);

        model.addAttribute("page_1", page_1);
        return "User/DatLichKham";
    }


    @PostMapping("/DatLichKham")
    public String addlichkham(@ModelAttribute("lichKham") LichKham lichkham,@RequestParam("date") String date,
    							@RequestParam("g-recaptcha-response") String recaptchaResponse,
    							@RequestParam("time") String time, BindingResult result, Model model,
                              Authentication authentication) throws MessagingException {
        if (result.hasErrors()) {
            return "404";
        }
//        if (!verifyCaptcha(recaptchaResponse)) {
//            model.addAttribute("captchaError", "Please complete the CAPTCHA verification.");
//            return "DatLichKham"; // Trả về trang đặt lịch nếu CAPTCHA không hợp lệ
//        }

        System.out.println("data về");

        LocalDate selectedDate = LocalDate.parse(date);
        LocalTime selectedTime = LocalTime.parse(time);
        LocalDateTime ngayGioDatKham = LocalDateTime.of(selectedDate, selectedTime);
        lichkham.setNgayGioDatKham(ngayGioDatKham);

        benhNhan = taiKhoanService.getBenhNhanByTenDN(authentication.getName());
        BenhNhan benhNhan1 = null;

        try {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            String email = oauth2User.getAttribute("email");

            benhNhan1 = benhnhanService.findByEmail(email);

            if (benhNhan1 != null) {
                lichkham.setBenhNhan(benhNhan1);
            } else {
                lichkham.setBenhNhan(benhNhan);
            }
        } catch (Exception e) {
            System.err.println("Error fetching patient information: " + e.getMessage());
            lichkham.setBenhNhan(benhNhan); // Sử dụng thông tin bệnh nhân từ tên đăng nhập nếu xảy ra lỗi
        }
        // Lấy thông tin bệnh nhân dựa trên tài khoản đăng nhập

//        if (benhNhan1 == null){
//            lichkham.setBenhNhan(benhNhan);
//        }else{
//            lichkham.setBenhNhan(benhNhan1);
//        }

         // Thay thế bằng bệnh nhân hiện đang đăng nhập
        lichkham.setTrangThai("Chưa khám");

        // Lưu `LichKham` trước để có ID (MaLK)
        lichKhamService.updateLichKham(lichkham);

        // Gán `LichKham` đã lưu vào `ChiTietLichKham`
        chiTietLichKham.setLichKham(lichkham);
        thanhToan.setLichKham(lichkham);
        thanhToan.setTrangthai("Chưa thanh toán");

        // Lưu `ChiTietLichKham` sau khi `LichKham` đã được lưu
        chiTietLichKhamService.updateChiTietLichKham(chiTietLichKham);

        thanhToanService.updateThanhToan(thanhToan);

        emailController.sendHtmlEmail2(lichkham.getEmail(),
                "Xác nhận đặt lịch khám thành công",
                date +" " + time);       // Gửi mail khi đặt lịch thành công



        // Cập nhật `ChiTietLichKham` cho `LichKham`
        lichkham.setChiTietLichKham(chiTietLichKham);
        return "redirect:/lichkhambenh";
    }

    @GetMapping("/api/getAvailableTimes")
    @ResponseBody
    public List<String> getAvailableTimes(@RequestParam("date") String date) {
        LocalDate selectedDate = LocalDate.parse(date);

        // Danh sách giờ làm việc trong ngày
        List<String> allTimes = List.of("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00",
                "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00");

        // Lấy danh sách các giờ đã đặt
        List<LocalTime> bookedTimes = lichKhamService.getBookedTimesByDate(selectedDate);
        // Lấy danh sách các ngày nghỉ
        List<LocalTime> holidayTimes = lichKhamService.getHolidayTimesByDate(selectedDate);

        // Lọc các giờ còn trống
        List<String> availableTimes = new ArrayList<>();
        for (String time : allTimes) {
            if (!bookedTimes.contains(LocalTime.parse(time)) && !holidayTimes.contains(LocalTime.parse(time))) { // Nếu cả 2 moc t.gian ko bi dat cho va ko nghi thi dat duoc
                availableTimes.add(time);
            }
        }
        System.out.println(availableTimes);
        return availableTimes;
    }

//    @GetMapping("/checkDate")
//    public ResponseEntity<Boolean> checkDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,Authentication authentication) {
//
//        benhNhan = taiKhoanService.getBenhNhanByTenDN(authentication.getName());
//
//        boolean hasAppointment = lichKhamService.hasAppointmentOnDate(benhNhan.getMaBN(), date);
//        return ResponseEntity.ok(hasAppointment);
//    }
}
