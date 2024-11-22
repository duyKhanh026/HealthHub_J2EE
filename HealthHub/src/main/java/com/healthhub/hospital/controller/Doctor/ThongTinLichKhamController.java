package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.ChiTietLichKham;
import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.Entity.ThanhToan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.ChiTietLichKhamService;
import com.healthhub.hospital.service.LichKhamService;

import com.healthhub.hospital.service.ThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ThongTinLichKhamController {

    private ChiTietLichKhamService chiTietLichKhamService;

    private LichKhamService lichKhamService;

    private BenhNhanService benhNhanService;

    private ThanhToanService thanhToanService;

    @Autowired
    public ThongTinLichKhamController(ChiTietLichKhamService chiTietLichKhamService, LichKhamService lichKhamService, BenhNhanService benhNhanService, ThanhToanService thanhToanService){
        this.chiTietLichKhamService = chiTietLichKhamService;
        this.lichKhamService = lichKhamService;
        this.benhNhanService = benhNhanService;
        this.thanhToanService = thanhToanService;
    }

    @GetMapping("/ThongTinLichKham")
    public String chiTietBenhNhan(@RequestParam("id") Integer id, Model model) {

        ChiTietLichKham chiTietList = chiTietLichKhamService.getChiTietLichKhamByMaLK(id);

        model.addAttribute("chiTiet", chiTietList);

        System.out.println(id);

        return "Doctor/ThongTinLichKham";
    }

    @PostMapping("/ThongTinLichKham")
    public String editChiTietLichKham(@ModelAttribute("chiTiet") ChiTietLichKham chiTietLichKham, BindingResult result,
                                      @RequestParam("maLK") Integer maLK) { // Nhận maLK từ form
        if (result.hasErrors()) {
            return "Doctor/404";
        }

        // Thiết lập LichKham cho ChiTietLichKham
        LichKham lichKham = lichKhamService.getLichKhambyID(maLK); // Lấy LichKham dựa trên maLK
        lichKham.setTrangThai("Đã khám");
        chiTietLichKham.setLichKham(lichKham);

        // Gọi service để cập nhật thông tin chi tiết lịch khám
        chiTietLichKhamService.updateChiTietLichKham(chiTietLichKham);

        Integer maBN = lichKham.getBenhNhan().getMaBN(); // Lấy mã bệnh nhân từ LichKham
        return "redirect:/ThongTinLichKham?id=" + maLK;
    }

}
