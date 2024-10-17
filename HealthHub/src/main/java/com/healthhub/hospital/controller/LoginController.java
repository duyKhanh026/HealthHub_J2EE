package com.healthhub.hospital.controller;

import com.healthhub.hospital.dao.TaiKhoanDAO;
import com.healthhub.hospital.model.TaiKhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private TaiKhoanDAO taiKhoanDAO;

    @PostMapping("/login")
    public String login(String tenDN, String matKhau, Model model) {
        TaiKhoan taiKhoan = taiKhoanDAO.findByUsername(tenDN);

        if (taiKhoan != null && taiKhoan.getMatkhau().equals(matKhau)) {
            // Đăng nhập thành công
            return "redirect:/dashboard"; // Điều hướng tới trang dashboard
        } else {
            // Đăng nhập thất bại
            model.addAttribute("error", "Invalid username or password");
            return "User/Login"; // Quay lại trang đăng nhập với thông báo lỗi
        }
    }
}
