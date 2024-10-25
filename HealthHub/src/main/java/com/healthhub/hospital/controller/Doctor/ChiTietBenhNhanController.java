package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.Repository.BenhNhanRepository;
import com.healthhub.hospital.Repository.ChiTietLichKhamRepository;
import com.healthhub.hospital.Repository.LichKhamRepository;
import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.ChiTietLichKham;
import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.ChiTietLichKhamService;
import com.healthhub.hospital.service.LichKhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
//
//        // Lấy chi tiết lịch khám từ từng lịch khám
//        for (LichKham lichKham : lichKhamList) {
//            List<ChiTietLichKham> chiTietList = chiTietLichKhamService.getChiTietLichKhamByMaLK(lichKham.getMaLK());
//            // Giả sử mỗi lịch khám chỉ có một chi tiết, bạn có thể thiết lập thêm nếu cần
//            if (!chiTietList.isEmpty()) {
//                lichKham.setChiTietLichKham(chiTietList.get(0)); // thêm chi tiet lich kham vao lich kham
//            }
//        }

        model.addAttribute("benhnhan", benhNhan);
        model.addAttribute("lichkhamList", lichKhamList);

        return "Doctor/ChiTietBenhNhan";
    }
}
