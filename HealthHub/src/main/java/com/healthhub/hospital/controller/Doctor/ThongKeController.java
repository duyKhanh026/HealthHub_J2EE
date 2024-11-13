package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.service.LichKhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ThongKeController {
    private final LichKhamService lichKhamService;

    @Autowired
    public ThongKeController(LichKhamService lichKhamService) {
        this.lichKhamService = lichKhamService;
    }

    @GetMapping("/ThongKe")
    public String thongKe(
            @RequestParam(value = "timePeriod", required = false, defaultValue = "day") String timePeriod, // day, week, month
            @RequestParam(value = "date", required = false) LocalDate date, // Ngày để thống kê
            @RequestParam(value = "status", required = false) String status, // Trạng thái để lọc
            Model model) {

        if (date == null) {
            date = LocalDate.now(); // Nếu không chọn ngày, mặc định là ngày hiện tại
        }

        long count = 0;
        long countStatus = 0;

        // Thống kê theo thời gian (ngày, tuần, tháng)
        if ("day".equals(timePeriod)) {
            count = lichKhamService.countByDate(date);
        } else if ("week".equals(timePeriod)) {
            count = lichKhamService.countByWeek(date);
        } else if ("month".equals(timePeriod)) {
            count = lichKhamService.countByMonth(date);
        }

        // Thống kê theo trạng thái
        if (status != null && !status.isEmpty()) {
            countStatus = lichKhamService.countByStatus(status);
        }

        // Thêm vào model để hiển thị
        model.addAttribute("timePeriod", timePeriod);
        model.addAttribute("date", date);
        model.addAttribute("count", count);
        model.addAttribute("countStatus", countStatus);
        model.addAttribute("status", status);

        return "Doctor/ThongKe"; // Trả về giao diện thống kê
    }
}
