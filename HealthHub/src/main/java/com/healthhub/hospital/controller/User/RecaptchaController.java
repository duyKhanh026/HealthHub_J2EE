package com.healthhub.hospital.controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.healthhub.hospital.service.RecaptchaService;


@RestController
@RequestMapping("/api/recaptcha")
public class RecaptchaController {
	@Value("${google.recaptcha.key}")
	private String SECRET_KEY;

    @Autowired
    private RecaptchaService recaptchaService;

    @PostMapping("/verify")
    public String verifyToken(@RequestParam String token, @RequestParam String action) {
        try {
            float score = recaptchaService.createAssessment(token, action);
            return "Recaptcha score: " + score;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}