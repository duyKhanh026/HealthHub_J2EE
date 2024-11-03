package com.healthhub.hospital.controller.User;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping({ "/login" })
    public String login(Model model, HttpServletRequest request) {
        // Kiểm tra cookie để tự động điền username
        String username = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
        System.out.println("Test1:"+username);
        model.addAttribute("username", username); // Gửi username vào model
        System.out.println("Test2:"+username);
        return "User/DangNhap"; // Trả về trang đăng nhập
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        @RequestParam(required = false) Boolean rememberMe,
                        HttpServletResponse response, Model model) {
        // Xác thực người dùng (nên sử dụng một dịch vụ để kiểm tra tài khoản)
        if (isValidUser(username, password)) {
            // Nếu rememberMe được chọn
            if (rememberMe != null && rememberMe) {
                // Tạo cookie để lưu trữ thông tin đăng nhập
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(7 * 24 * 60 * 60); // cookie có hiệu lực 7 ngày
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            // Chuyển hướng đến trang chính sau khi đăng nhập thành công
            return "redirect:/home";
        } else {
            // Xử lý lỗi đăng nhập (có thể trả về một thông báo lỗi)
            model.addAttribute("error", "Invalid username or password");
            return "User/DangNhap"; // Trả về trang đăng nhập với thông báo lỗi
        }
    }

    // Phương thức giả để kiểm tra thông tin đăng nhập
    private boolean isValidUser(String username, String password) {
        // Thực hiện xác thực người dùng ở đây (có thể gọi đến dịch vụ người dùng)
        return "admin".equals(username) && "password".equals(password); // Thay thế bằng logic xác thực thực tế
    }
}
