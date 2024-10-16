package com.healthhub.hospital.controller.Doctor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThongTinLichKhamController {

    @GetMapping("/ThongTinLichKham")
    public String thongtinlichkham(Model model){

        return "Doctor/ThongTinLichKham";
    }
}
