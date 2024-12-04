package com.healthhub.hospital.controller.Doctor;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
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
		
		String base64Image = convertImageToBase64(page.getImgdata());
        model.addAttribute("base64Image", base64Image);
        
        String base64Image2 = convertImageToBase64(page.getImgdata2());
        model.addAttribute("base64Image2", base64Image2);
		
		model.addAttribute("page", page);

		model.addAttribute("page_1", page_1);
		
		model.addAttribute("aboutus", aboutus);
		
		model.addAttribute("gallerys", gallerys);

        return "Doctor/Custom";  // Trả về view (Doctor/ThongKe.html)
    }
    
    @PostMapping("/Custom")
    public String submitData(
            @RequestPart("data") String jsonData,
            @RequestPart("file") MultipartFile file,
            @RequestPart("file2") MultipartFile file2, Model model
    ) throws IOException {
        // Deserialize JSON data
        SubmitRequest submitRequest = new ObjectMapper().readValue(jsonData, SubmitRequest.class);

        byte[] imageBytes = file.getBytes();
        System.out.println("Kích thước file: " + imageBytes.length + " bytes");
        
        byte[] imageBytes2 = file2.getBytes();
        System.out.println("Kích thước file: " + imageBytes.length + " bytes");

        // Gán lại dữ liệu ảnh
        PageCustomIndex data = submitRequest.getPageCustomIndex();
        data.setImgdata(imageBytes);
        data.setImgdata2(imageBytes2);

        // Xử lý logic lưu trữ khác như bạn đã làm
        pageCustomIndexService.saveOrUpdatePageCustomIndex(data);
        pageCustomIndex_1Service.deleteAll();
        for (PageCustomIndex_1 item : submitRequest.getPageCustomIndex_1()) {
            pageCustomIndex_1Service.save(item);
        }
        
        PageCustomIndex page = pageCustomIndexService.getFirstPageCustomIndex();
		List<PageCustomIndex_1> page_1 = pageCustomIndex_1Service.findAll();
		
		List<PageCustomAboutUs> aboutus = pageCustomAboutUsService.findAll();
		
		List<PageCustomGallery> gallerys = pageCustomGalleryService.findAll();
		
		model.addAttribute("page", page);

		model.addAttribute("page_1", page_1);
		
		model.addAttribute("aboutus", aboutus);
		
		model.addAttribute("gallerys", gallerys);

        return "Doctor/Custom";
    }
    public String convertImageToBase64(byte[] by) {
        byte[] imageBytes = by;
        return Base64.getEncoder().encodeToString(imageBytes);
    }
    

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
    public static class SubmitRequest {
    	private PageCustomIndex pageCustomIndex;
    	private List<PageCustomIndex_1> pageCustomIndex_1;
    }
}
