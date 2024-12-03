package com.healthhub.hospital.controller;

import com.healthhub.hospital.Entity.ThanhToan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.ThanhToanService;
import com.healthhub.hospital.service.zalo.ZaloPayService;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class ZaloPayController {

    private HttpSession session;
    private ThanhToanService thanhToanService;
    private ThanhToan thanhToan;

    public ZaloPayController(ThanhToanService thanhToanService, HttpSession session) {

        this.thanhToanService = thanhToanService;
        this.session = session;
    }

    @Autowired
    private ZaloPayService zaloPayService;

    @GetMapping("/zalopay")
    public String showForm(Model model) {

        Integer maTT = (Integer) session.getAttribute("maTT");

        thanhToan = thanhToanService.findbyid_thanhtoan(maTT);

        model.addAttribute("Name", thanhToan.getLichKham().getHoten());
        System.out.println(thanhToan.getLichKham().getHoten());

        return "zalopay"; // Hiển thị form
    }

    @PostMapping("payment/create-order")
    public String createOrder(@RequestParam String appUser,
                              @RequestParam int amount,
                              @RequestParam String description,
                              Model model) {

        thanhToan.setSoTien(amount);
        thanhToan.setHinhThucThanhToan("ZaloPay");
        thanhToan.setNgayThanhToan(LocalDateTime.now());
        thanhToan.setTrangthai("Đã thanh toán");
        thanhToanService.updateThanhToan(thanhToan);

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
