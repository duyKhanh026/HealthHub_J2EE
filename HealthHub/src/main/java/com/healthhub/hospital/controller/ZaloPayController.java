package com.healthhub.hospital.controller;

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
            String orderUrl = response.getString("order_url"); // Extract the order_url
            model.addAttribute("orderUrl", orderUrl); // Pass the extracted URL to the model
            return "order-success"; // Points to the HTML page
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "order-error";
        }
    }

}
