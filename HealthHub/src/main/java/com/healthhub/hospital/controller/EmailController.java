package com.healthhub.hospital.controller;

import com.healthhub.hospital.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller // Sử dụng @Controller để hỗ trợ trả về view
public class EmailController {

    @Autowired
    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @GetMapping("/send_email")
    public String emailForm() {
        return "send_email"; // Tên view để hiển thị giao diện gửi email
    }

    @PostMapping("/send_email")
    public String sendEmail(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("text") String text) {

        emailService.sendSimpleEmail(to, subject, text);
        return "redirect:/send_email?success"; // Chuyển hướng về trang gửi email với thông báo thành công
    }

    @PostMapping("/send_html_email")
    public String sendHtmlEmail(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("date") String date) throws MessagingException { // Truyền ngày hẹn vào form hoặc logic của bạn

        // Gọi hàm getHtmlTemplate và truyền ngày vào
        String htmlContent = getHtmlTemplate(date);

        // Gọi service để gửi email
        emailService.sendHtmlEmail(to, subject, htmlContent);
        return "redirect:/send_html_email?success";
    }

    public void sendHtmlEmail2(String to, String subject, String date) throws MessagingException {
        String htmlContent = getHtmlTemplate(date);

        // Gọi service để gửi email
        emailService.sendHtmlEmail(to, subject, htmlContent);
        System.out.println("Gửi thành công");
    }

    // Trong EmailController hoặc EmailService
    public String getHtmlTemplate(String date) {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title>Xác Nhận Đặt Lịch Khám</title>" +
                "<style>" +
                "body { font-family: 'Roboto', sans-serif; background-color: #f4f4f9; color: #333; }" +
                ".container { max-width: 600px; margin: 20px auto; background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); padding: 20px; }" +
                ".header { text-align: center; color: #007bff; font-size: 24px; font-weight: bold; margin-bottom: 10px; }" +
                ".sub-header { text-align: center; font-size: 18px; color: #333; margin-bottom: 20px; }" +
                ".content { line-height: 1.6; font-size: 16px; color: #555; }" +
                ".content p { margin: 10px 0; }" +
                ".content .date { font-weight: bold; color: #007bff; }" +
                ".footer { text-align: center; margin-top: 20px; font-size: 14px; color: #888; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<div class=\"header\">Phòng Khám nhóm 25</div>" +
                "<div class=\"sub-header\">Xác nhận đặt lịch thành công</div>" +
                "<div class=\"content\">" +
                "<p>Chào bạn,</p>" +
                "<p>Lịch khám của bạn đã được xác nhận thành công!</p>" +
                "<p>Lịch khám của bạn là ngày <span class=\"date\">" + date + "</span>.</p>" +
                "<p>Vui lòng đi khám đúng thời gian để thuận tiện nhất.</p>" +
                "<p>Nếu bạn có bất kỳ thắc mắc nào hoặc cần hỗ trợ, xin vui lòng liên hệ với chúng tôi qua email hoặc số điện thoại.</p>" +
                "</div>" +
                "<div class=\"footer\">" +
                "Cảm ơn bạn đã tin tưởng Phòng Khám nhóm 25!<br>" +
                "Chúng tôi hy vọng bạn có một trải nghiệm tốt với dịch vụ của chúng tôi." +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";
    }



}
