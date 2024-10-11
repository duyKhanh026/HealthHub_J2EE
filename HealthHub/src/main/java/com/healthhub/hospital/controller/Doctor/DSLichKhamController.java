package com.healthhub.hospital.controller.Doctor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DSLichKhamController {

    @GetMapping({ "/DSLichKham" })
    public String ListLichKham(Model model) {

        return "Doctor/DSLichKham";
    }

}
