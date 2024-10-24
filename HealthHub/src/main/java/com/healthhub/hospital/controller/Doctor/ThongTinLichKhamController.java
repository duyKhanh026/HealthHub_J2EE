package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.ChiTietLichKham;
import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.ChiTietLichKhamService;
import com.healthhub.hospital.service.LichKhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ThongTinLichKhamController {

    private ChiTietLichKhamService chiTietLichKhamService;

    private LichKhamService lichKhamService;

    private BenhNhanService benhNhanService;

    @Autowired
    public ThongTinLichKhamController(ChiTietLichKhamService chiTietLichKhamService, LichKhamService lichKhamService, BenhNhanService benhNhanService){
        this.chiTietLichKhamService = chiTietLichKhamService;
        this.lichKhamService = lichKhamService;
        this.benhNhanService = benhNhanService;
    }

    @GetMapping("/ThongTinLichKham")
    public String chiTietBenhNhan(@RequestParam("id") Integer id, @RequestParam("idbn") Integer idbn,Model model) {

        LichKham lichKhams = lichKhamService.getLichKhambyID(id);

        List<ChiTietLichKham> chiTietList = chiTietLichKhamService.getChiTietLichKhamByMaLK(id);

        BenhNhan benhNhan = benhNhanService.getBenhNhanById(idbn);

        model.addAttribute("benhNhan", benhNhan);

        model.addAttribute("chiTietList", chiTietList);

        model.addAttribute("lichkhams", lichKhams);

        System.out.println(id);

        return "Doctor/ThongTinLichKham";
    }
}
