package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.service.BenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("benhNhan", new BenhNhan());
        return "Doctor/DSBenhNhan";
    }


    // Xử lý form thêm bệnh nhân
    @PostMapping("/DSBenhNhan")
    public String addBenhNhan(@ModelAttribute("benhNhan") BenhNhan benhNhan, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "404";
        }
        benhNhanService.addBenhNhan(benhNhan);
        return "redirect:/DSBenhNhan";
    }

//
//    @PostMapping("/edit")
//    public String updateBenhNhan(@ModelAttribute("benhNhan") BenhNhan benhNhan, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "Doctor/edit";
//        }
//        benhNhanService.updateBenhNhan(benhNhan);
//        return "redirect:/DSBenhNhan";
//    }

}
