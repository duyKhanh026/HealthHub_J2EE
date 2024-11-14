package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.service.LichKhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class ThongKeController {
    @Autowired
    private LichKhamService lichKhamService;

    @GetMapping({ "/ThongKe" })
    public String login(Model model) {
        return "Doctor/ThongKe"; // Trả về trang đăng nhập
    }


}
