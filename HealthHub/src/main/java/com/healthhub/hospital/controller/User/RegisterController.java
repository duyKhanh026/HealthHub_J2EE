package com.healthhub.hospital.controller.User;

import com.healthhub.hospital.Repository.TaiKhoanRepository;
import com.healthhub.hospital.Entity.TaiKhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @GetMapping({ "/register" })
    public String register(Model model) {
        return "User/DangKy";

    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("confirmPassword") String confirmPassword,
                               Model model) {
        // Kiểm tra mật khẩu và mật khẩu xác nhận có trùng nhau không
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu không khớp!");
            return "User/DangKy"; // Đưa người dùng trở lại trang đăng ký nếu có lỗi
        }

        // Kiểm tra xem tên đăng nhập đã tồn tại chưa
        TaiKhoan existingAccount = taiKhoanRepository.getAccountByUserName(username);
        if (existingAccount != null) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
            return "User/DangKy";
        }

        // Tạo tài khoản mới
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenDN(username);
        taiKhoan.setMatkhau(passwordEncoder.encode(password)); // Mã hóa mật khẩu
        taiKhoan.setVaitro("user"); // Gán vai trò mặc định
        taiKhoanRepository.save(taiKhoan);

        // Chuyển hướng tới trang đăng nhập sau khi đăng ký thành công
        return "redirect:/login";
    }

}
