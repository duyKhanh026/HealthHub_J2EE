package com.healthhub.hospital.controller.User;

import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.service.LichKhamBenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LichKhamBenhController {
    @Autowired
    private LichKhamBenhService LKBService;

    @GetMapping("/lichkhambenh")
    public String danhSachLichKham(Model model) {
        System.out.println("Method danhSachLichKham is called.");
        int currentUserId = 9; // Tôi để 9 có sẵn trong database để test
        List<LichKham> lichKhamList = LKBService.getLichKhamByMaBN(currentUserId);
        System.out.println("Test:");
        System.out.println(lichKhamList);
        model.addAttribute("lichKhamList", lichKhamList);
        return "User/LichKhamBenh"; // Tên của view HTML bạn muốn render
    }

    // Phương thức để lấy MaBN của tài khoản hiện tại (cần implement)
    private int getCurrentUserId() {
        // Implement để lấy MaBN của tài khoản hiện tại
        return 9; // Ví dụ: trả về MaBN giả để thử nghiệm
    }


}
