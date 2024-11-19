package com.healthhub.hospital.controller.User;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.Entity.TaiKhoan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.LichKhamService;
import com.healthhub.hospital.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
    @Autowired
    private BenhNhanService benhnhanService;
    private BenhNhan benhnhan;

    @GetMapping("/lichkhambenh")
    public String danhSachLichKham(Model model, Authentication authentication) {
//        TaiKhoan tk = taiKhoanService.GetTKByID(authentication.getName());
        // Kiểm tra xem người dùng đang đăng nhập bằng tài khoản Google hay tài khoản mật khẩu thông thường
        if (authentication.getPrincipal() instanceof OAuth2User) {
            // Nếu đăng nhập bằng Google, lấy thông tin từ OAuth2User
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            String email = oauth2User.getAttribute("email");

            // Tìm bệnh nhân theo email từ Google
            benhnhan = benhnhanService.findByEmail(email);
        } else {
            // Nếu đăng nhập bằng tài khoản mật khẩu thông thường
            TaiKhoan tk = taiKhoanService.findByTenDN(authentication.getName());
            benhnhan = benhnhanService.getBenhNhanById(tk.getBenhNhan().getMaBN());
        }
        List<LichKham> lichKhamList = LKBService.getLichKhamByMaBN(benhnhan.getMaBN());

        model.addAttribute("lichKhamList", lichKhamList);
        return "User/LichKhamBenh"; // Tên của view HTML bạn muốn render
    }




}
