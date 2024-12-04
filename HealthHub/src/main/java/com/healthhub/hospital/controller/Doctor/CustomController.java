package com.healthhub.hospital.controller.Doctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.healthhub.hospital.Entity.PageCustomAboutUs;
import com.healthhub.hospital.Entity.PageCustomGallery;
import com.healthhub.hospital.Entity.PageCustomIndex;
import com.healthhub.hospital.Entity.PageCustomIndex_1;
import com.healthhub.hospital.service.PageCustomAboutUsService;
import com.healthhub.hospital.service.PageCustomGalleryService;
import com.healthhub.hospital.service.PageCustomIndexService;
import com.healthhub.hospital.service.PageCustomIndex_1Service;

@Controller
public class CustomController {
	@Autowired
	private PageCustomIndexService pageCustomIndexService;
	@Autowired
	private PageCustomIndex_1Service pageCustomIndex_1Service;
	@Autowired
	private PageCustomAboutUsService pageCustomAboutUsService;
	@Autowired
	private PageCustomGalleryService pageCustomGalleryService;

    @GetMapping("/Custom")
    public String showThongKePage(Model model) {
		PageCustomIndex page = pageCustomIndexService.getFirstPageCustomIndex();
		List<PageCustomIndex_1> page_1 = pageCustomIndex_1Service.findAll();
		
		List<PageCustomAboutUs> aboutus = pageCustomAboutUsService.findAll();
		
		List<PageCustomGallery> gallerys = pageCustomGalleryService.findAll();
		
		model.addAttribute("page", page);

		model.addAttribute("page_1", page_1);
		
		model.addAttribute("aboutus", aboutus);
		
		model.addAttribute("gallerys", gallerys);

        return "Doctor/Custom";  // Trả về view (Doctor/ThongKe.html)
    }
}
