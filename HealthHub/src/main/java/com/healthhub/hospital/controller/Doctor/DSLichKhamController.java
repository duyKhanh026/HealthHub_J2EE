package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.LichKhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DSLichKhamController {

    private final LichKhamService lichKhamService;

    @Autowired
    public DSLichKhamController(LichKhamService lichKhamService) {
        this.lichKhamService = lichKhamService;
    }

    @GetMapping({ "/DSLichKham" })
    public String ListLichKham(Model model) {
        List<LichKham> lichKhamList = lichKhamService.getAllLichKham();
        model.addAttribute("lichkhamList", lichKhamList);
        return "Doctor/DSLichKham";
    }

}
