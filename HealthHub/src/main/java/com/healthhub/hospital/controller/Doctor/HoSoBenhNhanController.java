package com.healthhub.hospital.controller.Doctor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HoSoBenhNhanController {

    @RequestMapping(value = { "/HoSoBenhNhan" }, method = RequestMethod.GET)
    public String HoSoBN(Model model) {

        return "Doctor/HoSoBenhNhan";
    }
}
