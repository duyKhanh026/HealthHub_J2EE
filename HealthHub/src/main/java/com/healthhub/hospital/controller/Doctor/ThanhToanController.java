package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.Entity.ThanhToan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.LichKhamService;
import com.healthhub.hospital.service.ThanhToanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ThanhToanController {

    private ThanhToanService thanhToanService;

    private LichKhamService lichKhamService;

    private LichKham lichKham;

    private HttpSession session;
    @Autowired
    public ThanhToanController(ThanhToanService thanhToanService, LichKhamService lichKhamService, HttpSession session) {
        this.thanhToanService = thanhToanService;
        this.lichKhamService = lichKhamService;
        this.session = session;
    }

    @GetMapping("/ThanhToan")
    public String thanhtoan(@RequestParam("id") Integer id, @RequestParam("maLK") Integer maLK, Model model) {
        // Lấy thông tin thanh toán dựa trên id
        ThanhToan thanhToan = thanhToanService.findbyid_thanhtoan(id);

        // Lấy thông tin lịch khám dựa trên maLK nếu cần
        lichKham = lichKhamService.getLichKhambyID(maLK);

        model.addAttribute("thanhToan", thanhToan);
        model.addAttribute("lichKham", lichKham);

        session.setAttribute("maTT", id);

        return "Doctor/ThanhToan";
    }


    @PostMapping("/ThanhToan")
    public String editThanhToan(@ModelAttribute("thanhToan") ThanhToan thanhToan, BindingResult result) {
        if (result.hasErrors()) {
            return "Doctor/404";
        }

        // Liên kết thanh toán với lịch khám
        thanhToan.setLichKham(lichKham);
        thanhToan.setNgayThanhToan(LocalDateTime.now());
        // Cập nhật thông tin thanh toán trong cơ sở dữ liệu
        thanhToanService.updateThanhToan(thanhToan);

        return "redirect:/ThanhToan?id=" + thanhToan.getMaTT() + "&maLK=" + thanhToan.getLichKham().getMaLK();
    }
}
