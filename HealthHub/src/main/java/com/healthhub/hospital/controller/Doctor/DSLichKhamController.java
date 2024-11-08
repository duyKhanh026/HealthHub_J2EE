package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.service.LichKhamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DSLichKhamController {

    private final LichKhamService lichKhamService;

    @Autowired
    public DSLichKhamController(LichKhamService lichKhamService) {
        this.lichKhamService = lichKhamService;
    }

    @GetMapping("/DSLichKham")
    public String ListLichKham(
            @RequestParam(value = "selectedDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate selectedDate,
            @RequestParam(value = "action", required = false) String action, // Nhận giá trị từ nút bấm
            Model model) {

        List<LichKham> lichKhamList;

        // Nếu nhấn nút "Xem toàn bộ lịch khám", lấy tất cả lịch khám
        if ("viewAll".equals(action)) {
            lichKhamList = lichKhamService.getAllLichKham();
        } else {
            // Nếu không chọn ngày, mặc định là ngày hiện tại
            if (selectedDate == null) {
                selectedDate = LocalDate.now();
            }
            // Lấy lịch khám theo ngày đã chọn
            lichKhamList = lichKhamService.getLichKhamByDate(selectedDate);
        }

        model.addAttribute("lichkhamList", lichKhamList);
        model.addAttribute("selectedDate", selectedDate); // Để giữ giá trị đã chọn

        return "Doctor/DSLichKham";
    }



    @GetMapping({ "/NgayNghi" })
	public String ListNgayNghi(Model model) {
        List<LichKham> lichnghi;

        lichnghi = lichKhamService.getAllDayOffAppointments();

        model.addAttribute("lichNghiList", lichnghi);


		return "Doctor/Ngaynghi";
	}
}
