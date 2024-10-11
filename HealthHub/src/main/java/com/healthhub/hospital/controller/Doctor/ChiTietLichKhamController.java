package com.healthhub.hospital.controller.Doctor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChiTietLichKhamController {
    @RequestMapping(value = { "/ChiTietLichKham" }, method = RequestMethod.GET)
    public String ChiTietLichKham(Model model) {

        return "Doctor/ChiTietLichKham";
    }
}
