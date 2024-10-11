package com.healthhub.hospital.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.healthhub.hospital.config.AppConfig;
import com.healthhub.hospital.dao.BenhNhanDAO;
import com.healthhub.hospital.model.BenhNhan;

@Controller
public class MainController {

	// Inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;
	
	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		
		BenhNhanDAO dao = context.getBean(BenhNhanDAO.class);
		SqlRowSet rowSet = dao.getBenhNhanSqlRowSet();
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

	
	@RequestMapping(value = { "/Login" }, method = RequestMethod.GET)
	public String login(Model model) {

		return "User/Login";
	}

	@RequestMapping(value = { "/make_appointment" }, method = RequestMethod.GET)
	public String make_appoint(Model model) {

		return "User/make_appointment";
	}

	@RequestMapping(value = { "/DangNhap" }, method = RequestMethod.GET)
	public String Dangnhap(Model model) {

		return "User/DangNhap";
	}

}