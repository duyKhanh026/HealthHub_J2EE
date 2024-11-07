package com.healthhub.hospital.controller;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.TaiKhoan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@Autowired
	private BenhNhanService benhnhanService;
	@Autowired
	private TaiKhoanService taiKhoanService;

	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;


	@GetMapping({ "/", "/index" })
	public String index(Model model, Authentication authentication) {
		TaiKhoan tk = taiKhoanService.GetTKByID(authentication.getName());

		BenhNhan benhnhan = benhnhanService.getBenhNhanById(tk.getBenhNhan().getMaBN());
		model.addAttribute("hoTen", benhnhan.getHoTen());
		return "User/index";
	}

}