package com.healthhub.hospital.controller;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.TaiKhoan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("hoTen")
public class MainController {
	@Value("${welcome.message}")
	private String message;
	@Value("${error.message}")
	private String errorMessage;
	@Autowired
	private TaiKhoanService taiKhoanService;
	@Autowired
	private BenhNhanService benhnhanService;

	@GetMapping({ "/", "/index" })
	public String index(Model model) {

		return "User/index";
	}

//	@ModelAttribute
//	public void addHoTenToModel(Model model, Authentication authentication) {
//		authentication = SecurityContextHolder.getContext().getAuthentication();
//
//		// Kiểm tra nếu người dùng đã đăng nhập
//		if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
//			TaiKhoan tk = taiKhoanService.GetTKByID(authentication.getName());
//			BenhNhan benhnhan = benhnhanService.getBenhNhanById(tk.getBenhNhan().getMaBN());
//			model.addAttribute("hoTen", benhnhan.getHoTen());
//		}
//	}

}