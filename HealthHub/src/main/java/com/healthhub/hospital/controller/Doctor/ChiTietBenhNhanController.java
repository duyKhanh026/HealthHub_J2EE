package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.ChiTietLichKhamService;
import com.healthhub.hospital.service.LichKhamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChiTietBenhNhanController {

    private BenhNhanService benhNhanService ;

    private LichKhamService lichKhamService ;

    private ChiTietLichKhamService chiTietLichKhamService;


    @Autowired
    public ChiTietBenhNhanController(BenhNhanService benhNhanService,LichKhamService lichKhamService, ChiTietLichKhamService chiTietLichKhamService ) {
        this.benhNhanService = benhNhanService;
        this.lichKhamService = lichKhamService;
        this.chiTietLichKhamService = chiTietLichKhamService;

    }


    @GetMapping("/ChiTietBenhNhan")
    public String chiTietBenhNhan(@RequestParam("id") Integer id, Model model) {
        // Lấy thông tin bệnh nhân từ service
        BenhNhan benhNhan = benhNhanService.getBenhNhanById(id);

        if (benhNhan == null) {
            return "error/404";
        }
//         Lấy lịch sử khám của bệnh nhân
        List<LichKham> lichKhamList = lichKhamService.getLichKhamByBenhNhanId(id);

        model.addAttribute("benhnhan", benhNhan);
        model.addAttribute("lichkhamList", lichKhamList);

        return "Doctor/ChiTietBenhNhan";
    }

    @PostMapping("/ChiTietBenhNhan")
    public String editBenhNhan(@ModelAttribute("benhnhan") BenhNhan benhNhan, BindingResult result) {
        if (result.hasErrors()) {
            return "404";
        }
        benhNhanService.updateBenhNhan(benhNhan);
        return "redirect:/ChiTietBenhNhan?id=" + benhNhan.getMaBN();
    }

}
