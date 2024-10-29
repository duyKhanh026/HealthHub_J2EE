package com.healthhub.hospital.controller;

import com.healthhub.hospital.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller // Sử dụng @Controller để hỗ trợ trả về view
public class EmailController {

    @Autowired
    private EmailService emailService;

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

}
