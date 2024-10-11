package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.config.AppConfig;
import com.healthhub.hospital.dao.BenhNhanDAO;
import com.healthhub.hospital.model.BenhNhan;
import com.healthhub.hospital.service.BenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DSBenhNhanController {

    private final BenhNhanService benhNhanService;

    @Autowired
    public DSBenhNhanController(BenhNhanService benhNhanService) {
        this.benhNhanService = benhNhanService;
    }

    @GetMapping("/DSBenhNhan")
    public String listBenhNhan(Model model) {
        List<BenhNhan> benhnhans = benhNhanService.getAllBenhNhan();
        model.addAttribute("benhnhans", benhnhans);
        return "Doctor/DSBenhNhan";
    }
}
