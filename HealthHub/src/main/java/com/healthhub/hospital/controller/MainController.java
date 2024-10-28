package com.healthhub.hospital.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.ChiTietLichKham;
import com.healthhub.hospital.Entity.LichKham;
//import com.healthhub.hospital.Entity.LichKham;
//import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.ChiTietLichKhamService;
import com.healthhub.hospital.service.LichKhamService;
//import com.healthhub.hospital.service.LichKhamService;

@Controller
public class MainController {
//	@Autowired
//	private BenhNhanService benhNhanserv;

	// Inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;


	@GetMapping({ "/", "/index" })
	public String index(Model model) {

//		List<BenhNhan> benhnhans = benhNhanserv.getAllBenhNhan();
//		model.addAttribute("benhnhans", benhnhans);


		return "User/index";
	}

//
//	@GetMapping({ "/make_appointment" })
//	public String make_appoint(Model model) {
//
//		return "User/make_appointment";
//	}


}