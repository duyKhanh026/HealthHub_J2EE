package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.model.BenhNhan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.ChiTietBenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ChiTietBenhNhanController {


    @RequestMapping(value = { "/ChiTietBenhNhan" }, method = RequestMethod.GET)
    public String ChiTietLichKham(Model model) {

        return "Doctor/ChiTietBenhNhan";
    }
}
