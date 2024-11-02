package com.healthhub.hospital.controller.User;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.ChiTietLichKham;
import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.Entity.TaiKhoan;
import com.healthhub.hospital.service.ChiTietLichKhamService;
import com.healthhub.hospital.service.LichKhamService;
import com.healthhub.hospital.service.TaiKhoanService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DatLichKhamController {
    private LichKhamService lichKhamService;

    private TaiKhoanService taiKhoanService;

    private ChiTietLichKhamService chiTietLichKhamService;

    private Authentication authentication;

    private BenhNhan benhNhan = new BenhNhan();

    private ChiTietLichKham chiTietLichKham = new ChiTietLichKham();

    public DatLichKhamController(LichKhamService lichKhamService, ChiTietLichKhamService chiTietLichKhamService,
                                 TaiKhoanService taiKhoanService) {
        this.lichKhamService = lichKhamService;
        this.chiTietLichKhamService = chiTietLichKhamService;
        this.taiKhoanService = taiKhoanService;

    }


    @GetMapping({ "/DatLichKham" })
    public String datlichKham(Model model, Authentication authentication) {
        model.addAttribute("lichKham", new LichKham() );
        this.authentication = authentication;
        return "User/DatLichKham";
    }

    @PostMapping("/DatLichKham")
    public String addlichkham(@ModelAttribute("lichKham") LichKham lichkham, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "404";
        }

        System.out.println("data về");

        // Lấy thông tin bệnh nhân dựa trên tài khoản đăng nhập
        benhNhan = taiKhoanService.getBenhNhanByTenDN(authentication.getName());
        lichkham.setBenhNhan(benhNhan); // Thay thế bằng bệnh nhân hiện đang đăng nhập
        lichkham.setTrangThai("Chưa khám");

        // Lưu `LichKham` trước để có ID (MaLK)
        lichKhamService.updateLichKham(lichkham);

        // Gán `LichKham` đã lưu vào `ChiTietLichKham`
        chiTietLichKham.setLichKham(lichkham);

        // Lưu `ChiTietLichKham` sau khi `LichKham` đã được lưu
        chiTietLichKhamService.updateChiTietLichKham(chiTietLichKham);

        // Cập nhật `ChiTietLichKham` cho `LichKham`
        lichkham.setChiTietLichKham(chiTietLichKham);
        return "redirect:/DatLichKham";
    }
}
