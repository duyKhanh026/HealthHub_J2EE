package com.healthhub.hospital.dao;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healthhub.hospital.Repository.BenhNhanRepository;
import com.healthhub.hospital.model.BenhNhan;


@Controller
@RequestMapping("/benhnhan")
public class BenhNhanController {
 
    private BenhNhanRepository benhnhanrepo;
 
    public BenhNhanController(BenhNhanRepository benhnhanrepo) {
        this.benhnhanrepo = benhnhanrepo;
    }
 
    @GetMapping
    public String getAllStudents(Model model) {
        List<BenhNhan> students = benhnhanrepo.findAll();
        model.addAttribute("benhnhans", students);
        return "benhnhans";
    }
    
}