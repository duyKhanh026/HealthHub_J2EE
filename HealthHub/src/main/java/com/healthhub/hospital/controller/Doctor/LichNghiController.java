package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.service.LichKhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class LichNghiController {

    private final LichKhamService lichKhamService;

    @Autowired
    public LichNghiController(LichKhamService lichKhamService) {
        this.lichKhamService = lichKhamService;
    }

    @GetMapping({ "/NgayNghi" })
    public String ListNgayNghi(Model model) {
        List<LichKham> lichnghi;

        lichnghi = lichKhamService.getAllDayOffAppointments();

        model.addAttribute("lichNghiList", lichnghi);


        return "Doctor/Ngaynghi";
    }

    @PostMapping("/NgayNghi")
    public String addNgayNghi(
            @RequestParam("ngayNghi") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngayNghi,
            @RequestParam("thoiGianNghi") String thoiGianNghi,
            Model model) {

        // Kết hợp ngày nghỉ và thời gian nghỉ thành LocalDateTime
        LocalDateTime ngayGioDatKham = LocalDateTime.parse(ngayNghi + "T" + thoiGianNghi);

        // Tạo đối tượng LichKham với trạng thái "DayOff"
        LichKham lichKham = new LichKham();
        lichKham.setNgayGioDatKham(ngayGioDatKham);
        lichKham.setTrangThai("DayOff");

        // Lưu vào database
        lichKhamService.updateLichKham(lichKham);

        // Lấy danh sách ngày nghỉ để hiển thị
        List<LichKham> lichnghi = lichKhamService.getAllDayOffAppointments();
        model.addAttribute("lichNghiList", lichnghi);

        return "Doctor/Ngaynghi";
    }

    @DeleteMapping("/NgayNghi/xoa")
    public String xoaNgayNghi(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        System.out.println("ID nhận được: " + id); // Log kiểm tra

        lichKhamService.deleteLichKhamById(id); // Xóa lịch nghỉ dựa trên ID
        redirectAttributes.addFlashAttribute("message", "Xóa lịch nghỉ thành công!");
        return "redirect:/NgayNghi"; // Redirect về trang danh sách ngày nghỉ
    }

}
