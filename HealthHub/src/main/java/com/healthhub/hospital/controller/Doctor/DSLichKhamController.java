package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.ChiTietLichKham;
import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.Entity.ThanhToan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.ChiTietLichKhamService;
import com.healthhub.hospital.service.LichKhamService;

import com.healthhub.hospital.service.ThanhToanService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class DSLichKhamController {

    private final LichKhamService lichKhamService;
    private final BenhNhanService benhNhanService;
    private final ChiTietLichKhamService chiTietLichKhamService;
    private final ThanhToanService thanhToanService;

    @Autowired
    public DSLichKhamController(LichKhamService lichKhamService, BenhNhanService benhNhanService,
                                ChiTietLichKhamService chiTietLichKhamService, ThanhToanService thanhToanService) {
        this.lichKhamService = lichKhamService;
        this.benhNhanService = benhNhanService;
        this.chiTietLichKhamService = chiTietLichKhamService;
        this.thanhToanService = thanhToanService;
    }

    @GetMapping("/DSLichKham")
    public String ListLichKham(
            @RequestParam(value = "selectedDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate selectedDate,
            @RequestParam(value = "action", required = false) String action, // Nhận giá trị từ nút bấm
            Model model, HttpServletRequest request) {

        model.addAttribute("requestURI", request.getRequestURI());  // Thêm URI vào model thay vì trực tiếp sử dụng #request

        List<LichKham> lichKhamList;

        // Nếu nhấn nút "Xem toàn bộ lịch khám", lấy tất cả lịch khám
        if ("viewAll".equals(action)) {
            lichKhamList = lichKhamService.findAllnotDayoff("DayOff");
        } else {
            // Nếu không chọn ngày, mặc định là ngày hiện tại
            if (selectedDate == null) {
                selectedDate = LocalDate.now();
            }
            // Lấy lịch khám theo ngày đã chọn
            lichKhamList = lichKhamService.getLichKhamByDate_NotOff(selectedDate);
        }

        model.addAttribute("lichkhamList", lichKhamList);
        model.addAttribute("selectedDate", selectedDate); // Để giữ giá trị đã chọn

        return "Doctor/DSLichKham";
    }

    @PostMapping("/DSLichKham")
    public String addlichkham(@ModelAttribute("lichKham") LichKham lichkham, @RequestParam("date") String date,
                              @RequestParam("time") String time, @RequestParam("ten") String ten,@RequestParam("sdt") String sdt,
                              BindingResult result, Model model) throws MessagingException {
        if (result.hasErrors()) {
            return "404";
        }

        System.out.println("data về" + ten + sdt + date + time);

        LocalDate selectedDate = LocalDate.parse(date);
        LocalTime selectedTime = LocalTime.parse(time);
        LocalDateTime ngayGioDatKham = LocalDateTime.of(selectedDate, selectedTime);

        BenhNhan benhNhan = new BenhNhan();

        if (benhNhanService.findBySDT(sdt) != null){        // nếu bệnh nhân đã tồn tại thì lấy bệnh nhân đó gán vào lịch khám mới
            benhNhan = benhNhanService.findBySDT(sdt);

        }else {
            benhNhan.setHoTen(ten);                      // còn nếu chưa tồn tại thì tạo mới bệnh nhân
            benhNhan.setSDT(sdt);
            benhNhanService.updateBenhNhan(benhNhan);
        }

        ChiTietLichKham chiTietLichKham = new ChiTietLichKham();
        ThanhToan thanhToan = new ThanhToan();

        lichkham.setBenhNhan(benhNhan); // Them benh nhan
        lichkham.setHoten(ten);
        lichkham.setSDT(sdt);
        lichkham.setNgayGioDatKham(ngayGioDatKham);
        lichkham.setTrangThai("Chưa khám");

        // Lưu `LichKham` trước để có ID (MaLK)
        lichKhamService.updateLichKham(lichkham);

        chiTietLichKham.setLichKham(lichkham);
        thanhToan.setLichKham(lichkham);


        // Lưu `ChiTietLichKham` sau khi `LichKham` đã được lưu
        chiTietLichKhamService.updateChiTietLichKham(chiTietLichKham);

        thanhToanService.updateThanhToan(thanhToan);


        // Cập nhật `ChiTietLichKham` cho `LichKham`
        lichkham.setChiTietLichKham(chiTietLichKham);
        return "redirect:/DSLichKham";
    }




}
