package com.healthhub.hospital.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	// Inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("message", message);

		return "index";
	}

	
	@RequestMapping(value = { "/Login" }, method = RequestMethod.GET)
	public String login(Model model) {

		return "Login";
	}

	@RequestMapping(value = { "/make_appointment" }, method = RequestMethod.GET)
	public String make_appoint(Model model) {

		return "make_appointment";
	}

	@RequestMapping(value = { "/DangNhap" }, method = RequestMethod.GET)
	public String Dangnhap(Model model) {

		return "DangNhap";
	}

	@RequestMapping(value = { "/DSLichKham" }, method = RequestMethod.GET)
	public String ListLichKham(Model model) {

		return "DSLichKham";
	}
}