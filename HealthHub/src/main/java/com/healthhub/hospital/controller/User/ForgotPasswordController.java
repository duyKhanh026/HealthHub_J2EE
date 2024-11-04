package com.healthhub.hospital.controller.User;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.PasswordResetToken;
import com.healthhub.hospital.Entity.TaiKhoan;
import com.healthhub.hospital.Repository.BenhNhanRepository;
import com.healthhub.hospital.Repository.PasswordResetTokenRepository;
import com.healthhub.hospital.Repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender; // Tiêm dịch vụ gửi mail
    @Autowired
    private BenhNhanRepository benhNhanRepository; // Inject repository
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  TaiKhoanRepository taikhoanRepository;

    @GetMapping("/forgot_password")
    public String showForgotPasswordPage() {
        return "User/forgot_password"; // Đường dẫn đến file forgot_password.html
    }

    @GetMapping("/reset_password")
    public String showResetPasswordPage(@RequestParam("token") String token, Model model) {
        System.out.println("Day moi la vd nay: "+token);
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);
        System.out.println("O day moi dung: "+resetToken.getId());
        if (resetToken == null || resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", "Token không hợp lệ hoặc đã hết hạn.");
            return "User/DangNhap"; // Trở về trang đăng nhập nếu token không hợp lệ
        }

        model.addAttribute("token", token); // Thêm token vào model để sử dụng trong form
        return "User/reset_password"; // Trả về trang reset_password
    }
    @PostMapping("/forgot_password")
    public String handleForgotPassword(@RequestParam String email, Model model) {
        // Kiểm tra xem email có tồn tại trong cơ sở dữ liệu hay không
        if (isEmailRegistered(email)) {
            // Gửi email với liên kết đặt lại mật khẩu
            String resetLink = generatePasswordResetLink(email); // Tạo liên kết đặt lại mật khẩu
            sendPasswordResetEmail(email, resetLink); // Gửi email
            model.addAttribute("message", "A password reset link has been sent to your email.");
        } else {
            model.addAttribute("error", "Email not found. Please register if you haven't.");
        }
        return "User/DangNhap"; // Trở về trang đăng nhập
    }

    @PostMapping("/reset_password")
    public String processResetPassword(@RequestParam("token") String token,
                                       @RequestParam("password") String password,
                                       @RequestParam("confirmPassword") String confirmPassword,
                                       Model model) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);

        if (resetToken == null || resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", "Token không hợp lệ hoặc đã hết hạn.");
            return "User/reset_password";
        }

        BenhNhan benhNhan = resetToken.getBenhNhan();
        if (password.equals(confirmPassword)) {
            TaiKhoan taiKhoan = benhNhan.getTaiKhoan();
            taiKhoan.setMatkhau(passwordEncoder.encode(password));
            taikhoanRepository.save(taiKhoan);

            passwordResetTokenRepository.delete(resetToken);
            model.addAttribute("message", "Mật khẩu đã được thay đổi thành công.");
            return "User/DangNhap";
        } else {
            model.addAttribute("error", "Mật khẩu và xác nhận mật khẩu không khớp.");
            return "User/reset_password";
        }
    }

    private boolean isEmailRegistered(String email) {
        // Kiểm tra xem email đã đăng ký trong cơ sở dữ liệu chưa
        return benhNhanRepository.findByEmail(email) != null; // Kiểm tra xem có bệnh nhân nào với email này không
    }

    private String generatePasswordResetLink(String email) {
        BenhNhan benhNhan = benhNhanRepository.findByEmail(email);
        System.out.println(benhNhan.getMaBN()+",,,"+benhNhan.getSDT());
        String token = generateRandomToken();
        System.out.println("Token: "+ token);
        PasswordResetToken resetToken = new PasswordResetToken(token, benhNhan, LocalDateTime.now().plusHours(1));
        System.out.println("Coi thu token: "+resetToken.getId()+",,,"+resetToken.getExpiryDate());
        passwordResetTokenRepository.save(resetToken);
        return "http://localhost:8080/reset_password?token=" + token;
    }
    private String generateRandomToken() {
        // Logic để tạo token ngẫu nhiên (có thể sử dụng UUID)
        return java.util.UUID.randomUUID().toString();
    }

    private void sendPasswordResetEmail(String email, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the link below:\n" + resetLink);
        mailSender.send(message);
    }


}
