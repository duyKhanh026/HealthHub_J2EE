package com.healthhub.hospital.controller.User;

import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.Entity.TaiKhoan;
import com.healthhub.hospital.service.LichKhamService;
import com.healthhub.hospital.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("hoTen")
public class LichKhamController {
    @Autowired
    private LichKhamService LKBService;
    @Autowired
    private TaiKhoanService taiKhoanService;

    @GetMapping("/lichkhambenh")
    public String danhSachLichKham(Model model, Authentication authentication) {
        TaiKhoan tk = taiKhoanService.GetTKByID(authentication.getName());

        List<LichKham> lichKhamList = LKBService.getLichKhamByMaBN(tk.getBenhNhan().getMaBN());

        model.addAttribute("lichKhamList", lichKhamList);
        return "User/LichKhamBenh"; // Tên của view HTML bạn muốn render
    }




}
