package com.healthhub.hospital.controller.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healthhub.hospital.Entity.ChiTietLichKham;
import com.healthhub.hospital.Entity.LichKham;
import com.healthhub.hospital.service.ChiTietLichKhamService;
import com.healthhub.hospital.service.LichKhamService;

@Controller
public class LichKhamUserController {
	
	private LichKhamService lichKhamService;
	
	private ChiTietLichKhamService chiTietLichKhamService;

	public LichKhamUserController(LichKhamService lichKhamService,ChiTietLichKhamService chiTietLichKhamService) {
		this.lichKhamService = lichKhamService;
		this.chiTietLichKhamService = chiTietLichKhamService;
	}
	
	@GetMapping({ "/LichKhamLS" })
	public String lichKham(Model model) {
		List<LichKham> listkham = new ArrayList<>();
		listkham = lichKhamService.getAllLichKham();
		model.addAttribute("listkham", listkham);
	    model.addAttribute("lichkhamchitiet", new ChiTietLichKham());
		return "User/LichKham";
	}
	
	@GetMapping("/LichKhamLS/chiTiet")
	public String chiTietLichKham(@RequestParam Integer id, Model model) {
		ChiTietLichKham lk = chiTietLichKhamService.getChiTietLichKhamByMaLK(id);

	    if (lk == null) {
	        return "error/404";
	    }

	    model.addAttribute("lichkhamchitiet", lk);
	    return "User/LichKham :: #lichkhamContent";  // Chỉ trả về fragment chứa chi tiết
	}
}
