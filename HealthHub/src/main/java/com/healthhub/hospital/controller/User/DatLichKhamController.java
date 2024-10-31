package com.healthhub.hospital.controller.User;

import com.healthhub.hospital.Entity.ChiTietLichKham;
import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.service.ChiTietLichKhamService;
import com.healthhub.hospital.service.LichKhamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DatLichKhamController {
    private LichKhamService lichKhamService;

    private ChiTietLichKhamService chiTietLichKhamService;

    public DatLichKhamController(LichKhamService lichKhamService, ChiTietLichKhamService chiTietLichKhamService) {
        this.lichKhamService = lichKhamService;
        this.chiTietLichKhamService = chiTietLichKhamService;
    }

    @GetMapping({ "/DatLichKham" })
    public String datlichKham(Model model) {

        return "User/DatLichKham";
    }
}
