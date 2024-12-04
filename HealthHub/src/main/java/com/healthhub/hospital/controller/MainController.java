package com.healthhub.hospital.controller;

import com.healthhub.hospital.Entity.BenhNhan;
import com.healthhub.hospital.Entity.PageCustomAboutUs;
import com.healthhub.hospital.Entity.PageCustomGallery;
import com.healthhub.hospital.Entity.PageCustomIndex;
import com.healthhub.hospital.Entity.PageCustomIndex_1;
import com.healthhub.hospital.Entity.TaiKhoan;
import com.healthhub.hospital.service.BenhNhanService;
import com.healthhub.hospital.service.PageCustomAboutUsService;
import com.healthhub.hospital.service.PageCustomGalleryService;
import com.healthhub.hospital.service.PageCustomIndexService;
import com.healthhub.hospital.service.PageCustomIndex_1Service;
import com.healthhub.hospital.service.TaiKhoanService;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private PageCustomIndexService pageCustomIndexService;
	@Autowired
	private PageCustomIndex_1Service pageCustomIndex_1Service;
	@Autowired
	private PageCustomAboutUsService pageCustomAboutUsService;
	@Autowired
	private PageCustomGalleryService pageCustomGalleryService;
	
	private BenhNhan benhnhan;
	
	public String convertImageToBase64(byte[] by) {
        byte[] imageBytes = by;
        return Base64.getEncoder().encodeToString(imageBytes);
    }
	
	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		PageCustomIndex page = pageCustomIndexService.getFirstPageCustomIndex();
		List<PageCustomIndex_1> page_1 = pageCustomIndex_1Service.findAll();
		
		List<PageCustomAboutUs> aboutus = pageCustomAboutUsService.findAll();
		
		List<PageCustomGallery> gallerys = pageCustomGalleryService.findAll();


		String base64Image = convertImageToBase64(page.getImgdata());
        model.addAttribute("base64Image", base64Image);
        
        String base64Image2 = convertImageToBase64(page.getImgdata2());
        model.addAttribute("base64Image2", base64Image2);
		
		model.addAttribute("page", page);

		model.addAttribute("page_1", page_1);
		
		model.addAttribute("aboutus", aboutus);
		
		model.addAttribute("gallerys", gallerys);

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