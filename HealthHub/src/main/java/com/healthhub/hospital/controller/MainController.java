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
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("hoTen")
public class MainController {
	@Autowired
	private TaiKhoanService taiKhoanService;
	@Autowired
	private BenhNhanService benhnhanService;
	private BenhNhan benhnhan;

	@GetMapping({ "/", "/index" })
	public String index(Model model) {

		return "User/index";
	}

	@ModelAttribute
	public void addHoTenToModel(Model model, Authentication authentication) {
		authentication = SecurityContextHolder.getContext().getAuthentication();

		// Kiểm tra nếu người dùng đã đăng nhập
		if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
			String usernameOrEmail = authentication.getName();

			TaiKhoan tk;

			// Kiểm tra nếu đăng nhập qua Google (OAuth2User)
			if (authentication.getPrincipal() instanceof OAuth2User) {
				OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
				String email = oAuth2User.getAttribute("email");
				benhnhan = benhnhanService.findByEmail(email);
				tk = taiKhoanService.findByMaBN(benhnhan); // Hàm tìm tài khoản qua email
			} else {
				// Đăng nhập thông thường
				tk = taiKhoanService.findByTenDN(usernameOrEmail);
			}

			if (tk != null && tk.getBenhNhan() != null) {
				BenhNhan benhnhan = benhnhanService.getBenhNhanById(tk.getBenhNhan().getMaBN());
				model.addAttribute("hoTen", benhnhan.getHoTen());
			}
		}
	}

}