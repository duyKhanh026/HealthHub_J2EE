package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.service.zalo.ZaloPayService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ZaloPayController {

    @Autowired
    private ZaloPayService zaloPayService;

    @GetMapping("/zalopay")
    public String showForm() {
        return "zalopay"; // Hiển thị form
    }

    @PostMapping("payment/create-order")
    public String createOrder(@RequestParam String appUser,
                              @RequestParam int amount,
                              @RequestParam String description,
                              Model model) {
        try {
            JSONObject response = zaloPayService.createOrder(appUser, amount, description);
            model.addAttribute("response", response.toString());
            return "order-success"; // Trỏ đến trang hiển thị kết quả
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "order-error"; // Trỏ đến trang báo lỗi
        }
    }
}
