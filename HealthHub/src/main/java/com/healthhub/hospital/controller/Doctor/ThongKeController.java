package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.service.LichKhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class ThongKeController {

    @Autowired
    private LichKhamService lichKhamService;

    @GetMapping("/ThongKe")
    public String showThongKePage(Model model) {
        return "Doctor/ThongKe";  // Trả về view (Doctor/ThongKe.html)
    }

    @GetMapping("/ThongKe/data")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getStatistics(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") String startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") String endDate,
            @RequestParam(required = false) String status) {

        // Chuyển đổi từ String sang LocalDateTime
        LocalDateTime startDateTime = LocalDateTime.parse(startDate);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate);

        // Gọi dịch vụ để lấy thống kê dựa trên khoảng thời gian và trạng thái
        List<Object[]> stats = lichKhamService.getStatisticsByDateRangeAndStatus(startDateTime, endDateTime, status);

        List<String> labels = new ArrayList<>();
        List<Long> data = new ArrayList<>();

        for (Object[] record : stats) {
            labels.add(record[0].toString()); // Ngày
            data.add((Long) record[1]);      // Số lượng lịch khám
        }

        Map<String, Object> response = new HashMap<>();
        response.put("labels", labels);
        response.put("data", data);

        return ResponseEntity.ok(response);
    }
}
