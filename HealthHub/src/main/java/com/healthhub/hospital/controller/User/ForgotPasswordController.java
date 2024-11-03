package com.healthhub.hospital.controller.User;

import com.healthhub.hospital.Repository.BenhNhanRepository;
import com.healthhub.hospital.Repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender; // Tiêm dịch vụ gửi mail
    @Autowired
    private BenhNhanRepository benhNhanRepository; // Inject repository

    @GetMapping("/forgot_password")
    public String showForgotPasswordPage() {
        return "User/forgot_password"; // Đường dẫn đến file forgot_password.html
    }

    @PostMapping("/forgot_password")
    public String handleForgotPassword(@RequestParam String email, Model model) {
        // Logic để xử lý quên mật khẩu
        // Kiểm tra xem email có tồn tại trong cơ sở dữ liệu hay không
        if (isEmailRegistered(email)) {
            // Gửi email với liên kết đặt lại mật khẩu
            String resetLink = generatePasswordResetLink(email); // Tạo liên kết đặt lại mật khẩu
            sendPasswordResetEmail(email, resetLink); // Gửi email
            model.addAttribute("message", "A password reset link has been sent to your email.");
        } else {
            model.addAttribute("error", "Email not found. Please register if you haven't.");
        }
        return "User/login"; // Trở về trang đăng nhập
    }


    private boolean isEmailRegistered(String email) {
        // Kiểm tra xem email đã đăng ký trong cơ sở dữ liệu chưa
        return benhNhanRepository.findByEmail(email) != null; // Kiểm tra xem có bệnh nhân nào với email này không
    }

    private String generatePasswordResetLink(String email) {
        // Tạo liên kết đặt lại mật khẩu (có thể bao gồm token để xác thực)
        return "http://localhost:8080/reset_password?email=" + email + "&token=someRandomToken";
    }

    private void sendPasswordResetEmail(String email, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the link below:\n" + resetLink);
        mailSender.send(message);
    }


}
