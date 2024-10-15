package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.config.AppConfig;
import com.healthhub.hospital.dao.BenhNhanDAO;
import com.healthhub.hospital.model.BenhNhan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.ChiTietBenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChiTietBenhNhanController {

    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);



    private  BenhNhanService benhNhanService = new BenhNhanService(context.getBean(BenhNhanDAO.class));


    @GetMapping("/ChiTietBenhNhan")
    public String chiTietBenhNhan(@RequestParam("id") Integer id, Model model) {
        // Lấy thông tin bệnh nhân từ service dựa trên ID
        BenhNhan benhNhan = benhNhanService.getBenhNhanById(id);
        if (benhNhan == null) {
            // Xử lý khi không tìm thấy bệnh nhân, ví dụ chuyển tới trang lỗi
            return "error/404";
        }
        model.addAttribute("benhnhan", benhNhan);

        // Trả về trang chi tiết bệnh nhân
        return "Doctor/ChiTietBenhNhan";
    }
}
