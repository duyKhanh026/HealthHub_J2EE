package com.healthhub.hospital.controller.Doctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.healthhub.hospital.Entity.PageCustomAboutUs;
import com.healthhub.hospital.Entity.PageCustomGallery;
import com.healthhub.hospital.Entity.PageCustomIndex;
import com.healthhub.hospital.Entity.PageCustomIndex_1;
import com.healthhub.hospital.service.PageCustomAboutUsService;
import com.healthhub.hospital.service.PageCustomGalleryService;
import com.healthhub.hospital.service.PageCustomIndexService;
import com.healthhub.hospital.service.PageCustomIndex_1Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    
    @PostMapping("/Custom")
    public String submitData(@RequestBody SubmitRequest submitRequest, Model model) {

        // Lấy dữ liệu từ SubmitRequest
        PageCustomIndex data = submitRequest.getPageCustomIndex();
        List<PageCustomIndex_1> pageCustomIndex1List = submitRequest.getPageCustomIndex_1();

        // Lưu hoặc cập nhật dữ liệu PageCustomIndex
        pageCustomIndexService.saveOrUpdatePageCustomIndex(data);
        pageCustomIndex_1Service.deleteAll();
        for(int i = 0; i < pageCustomIndex1List.size();i++) {
        	pageCustomIndex_1Service.save(pageCustomIndex1List.get(i));
        }

        // Lấy các dữ liệu khác từ các service
        PageCustomIndex page = pageCustomIndexService.getFirstPageCustomIndex();
        List<PageCustomIndex_1> page_1 = pageCustomIndex_1Service.findAll();
        List<PageCustomAboutUs> aboutus = pageCustomAboutUsService.findAll();
        List<PageCustomGallery> gallerys = pageCustomGalleryService.findAll();

        // Thêm các dữ liệu vào model để hiển thị
        model.addAttribute("page", page);
        model.addAttribute("page_1", page_1);
        model.addAttribute("aboutus", aboutus);
        model.addAttribute("gallerys", gallerys);

        // Trả về view
        return "Doctor/Custom"; // Tên tệp HTML cần hiển thị (Custom.html)
    }

    

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
    public static class SubmitRequest {
    	private PageCustomIndex pageCustomIndex;
    	private List<PageCustomIndex_1> pageCustomIndex_1;
    }
}
