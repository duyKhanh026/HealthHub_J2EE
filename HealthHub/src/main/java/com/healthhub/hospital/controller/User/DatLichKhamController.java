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

    private ChiTietLichKhamService chiTietLichKhamService;

    private TaiKhoanService taiKhoanService;

    private BenhNhan benhNhan = new BenhNhan();

    private Authentication authentication;

    public DatLichKhamController(LichKhamService lichKhamService, ChiTietLichKhamService chiTietLichKhamService) {
        this.lichKhamService = lichKhamService;
        this.chiTietLichKhamService = chiTietLichKhamService;
    }


    @GetMapping({ "/DatLichKham" })
    public String datlichKham(Model model) {
        model.addAttribute("lichKham", new LichKham() );
        return "User/DatLichKham";
    }

    @PostMapping("/DatLichKham")
    public String addlichkham(@ModelAttribute("lichKham") LichKham lichkham, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "404";
        }

        System.out.println("data về");

        benhNhan.setMaBN(1);


//        benhNhan = taiKhoanService.getBenhNhan(authentication.getName());


        lichkham.setBenhNhan(benhNhan); // Thay the bang benhnhan hien dang dang nhap
        lichkham.setTrangThai("Chưa khám");

        lichKhamService.updateLichKham(lichkham);
        return "redirect:/DatLichKham";
    }
}