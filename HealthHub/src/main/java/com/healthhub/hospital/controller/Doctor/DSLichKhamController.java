package com.healthhub.hospital.controller.Doctor;

import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.LichKhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
            Model model) {

        // Set default to current date if no date is selected
        if (selectedDate == null) {
            selectedDate = LocalDate.now();
        }

        List<LichKham> lichKhamList = lichKhamService.getLichKhamByDate(selectedDate);

        model.addAttribute("lichkhamList", lichKhamList);
        model.addAttribute("selectedDate", selectedDate);

        return "Doctor/DSLichKham";
    }


    @GetMapping({ "/NgayNghi" })
	public String ListNgayNghi(Model model) {

		return "Doctor/Ngaynghi";
	}
}
