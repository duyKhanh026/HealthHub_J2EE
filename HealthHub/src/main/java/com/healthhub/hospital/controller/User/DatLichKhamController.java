package com.healthhub.hospital.controller.User;

import com.healthhub.hospital.Entity.*;
import com.healthhub.hospital.controller.EmailController;
import com.healthhub.hospital.service.ChiTietLichKhamService;
import com.healthhub.hospital.service.LichKhamService;
import com.healthhub.hospital.service.TaiKhoanService;
import com.healthhub.hospital.service.ThanhToanService;
import jakarta.mail.MessagingException;
import org.springframework.security.core.Authentication;
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
    private LichKhamService lichKhamService;

    private TaiKhoanService taiKhoanService;

    private ChiTietLichKhamService chiTietLichKhamService;

    private ThanhToanService thanhToanService;

    private Authentication authentication;

    private BenhNhan benhNhan = new BenhNhan();

    private ChiTietLichKham chiTietLichKham = new ChiTietLichKham();

    private ThanhToan thanhToan = new ThanhToan();

    private EmailController emailController;

    public DatLichKhamController(LichKhamService lichKhamService, ChiTietLichKhamService chiTietLichKhamService,
                                 TaiKhoanService taiKhoanService, ThanhToanService thanhToanService, EmailController emailController) {
        this.lichKhamService = lichKhamService;
        this.chiTietLichKhamService = chiTietLichKhamService;
        this.taiKhoanService = taiKhoanService;
        this.thanhToanService = thanhToanService;
        this.emailController = emailController;

    }


    @GetMapping({ "/DatLichKham" })
    public String datlichKham(Model model, Authentication authentication) {
        model.addAttribute("lichKham", new LichKham() );
        this.authentication = authentication;
        return "User/DatLichKham";
    }

    @PostMapping("/DatLichKham")
    public String addlichkham(@ModelAttribute("lichKham") LichKham lichkham,@RequestParam("date") String date,
                              @RequestParam("time") String time, BindingResult result, Model model) throws MessagingException {
        if (result.hasErrors()) {
            return "404";
        }

        System.out.println("data về");

        LocalDate selectedDate = LocalDate.parse(date);
        LocalTime selectedTime = LocalTime.parse(time);
        LocalDateTime ngayGioDatKham = LocalDateTime.of(selectedDate, selectedTime);
        lichkham.setNgayGioDatKham(ngayGioDatKham);


        // Lấy thông tin bệnh nhân dựa trên tài khoản đăng nhập
        benhNhan = taiKhoanService.getBenhNhanByTenDN(authentication.getName());
        lichkham.setBenhNhan(benhNhan); // Thay thế bằng bệnh nhân hiện đang đăng nhập
        lichkham.setTrangThai("Chưa khám");

        // Lưu `LichKham` trước để có ID (MaLK)
        lichKhamService.updateLichKham(lichkham);

        // Gán `LichKham` đã lưu vào `ChiTietLichKham`
        chiTietLichKham.setLichKham(lichkham);
        thanhToan.setLichKham(lichkham);


        // Lưu `ChiTietLichKham` sau khi `LichKham` đã được lưu
        chiTietLichKhamService.updateChiTietLichKham(chiTietLichKham);

        thanhToanService.updateThanhToan(thanhToan);

        emailController.sendHtmlEmail2(lichkham.getEmail(),
                "Xác nhận đặt lịch khám thành công",
                date +" " + time);       // Gửi mail khi đặt lịch thành công



        // Cập nhật `ChiTietLichKham` cho `LichKham`
        lichkham.setChiTietLichKham(chiTietLichKham);
        return "redirect:/DatLichKham";
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
}
