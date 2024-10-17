package com.healthhub.hospital.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healthhub.hospital.config.AppConfig;
import com.healthhub.hospital.Repository.BenhNhanDAO;
import com.healthhub.hospital.Repository.ChiTietLichKhamRepository;
import com.healthhub.hospital.Repository.LichKhamRepository;
import com.healthhub.hospital.model.BenhNhan;
import com.healthhub.hospital.model.ChiTietLichKham;
import com.healthhub.hospital.model.LichKham;
import com.healthhub.hospital.service.ChiTietLichKhamService;
import com.healthhub.hospital.service.LichKhamService;

@Controller
public class MainController {
	@Autowired
	private BenhNhanDAO benhNhanDAO;

	@Autowired
	private LichKhamService lichKhamService;

	@Autowired
	private ChiTietLichKhamService chiTietLichKhamService;


	// Inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;


	@GetMapping({ "/", "/index" })
	public String index(Model model) {

		SqlRowSet rowSet = benhNhanDAO.getBenhNhanSqlRowSet();
		List<BenhNhan> benhnhans = new ArrayList<BenhNhan>();
		while (rowSet.next()) {
			BenhNhan bn = new BenhNhan();
			bn.setMaBN(rowSet.getInt(1));
			bn.setHoTen(rowSet.getString(2));
			bn.setNgaySinh(rowSet.getDate(3));
			benhnhans.add(bn);
        }
		model.addAttribute("benhnhans", benhnhans);


		return "User/index";
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
		ChiTietLichKham lk = chiTietLichKhamService.getChiTietLichKhamByMaLK(id).get(0);

	    if (lk == null) {
	        return "error/404";
	    }

	    model.addAttribute("lichkhamchitiet", lk);
	    return "User/LichKham :: #lichkhamContent";  // Chỉ trả về fragment chứa chi tiết
	}
	
	@GetMapping({ "/login" })
	public String login(Model model) {

		return "User/DangNhap";
	}

	@GetMapping({ "/make_appointment" })
	public String make_appoint(Model model) {

		return "User/make_appointment";
	}


}