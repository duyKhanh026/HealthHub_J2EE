package com.healthhub.hospital.controller;

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

		return "User/index";
	}

	
	@RequestMapping(value = { "/Login" }, method = RequestMethod.GET)
	public String login(Model model) {

		return "User/DangNhap";
	}

	@RequestMapping(value = { "/make_appointment" }, method = RequestMethod.GET)
	public String make_appoint(Model model) {

		return "User/make_appointment";
	}


	@RequestMapping(value = { "/DSLichKham" }, method = RequestMethod.GET)
	public String ListLichKham(Model model) {

		return "Doctor/DSLichKham";
	}

	@RequestMapping(value = { "/DSBenhNhan" }, method = RequestMethod.GET)
	public String ListBenhNhan(Model model) {

		return "Doctor/DSBenhNhan";
	}

	@RequestMapping(value = { "/HoSoBenhNhan" }, method = RequestMethod.GET)
	public String HoSoBN(Model model) {

		return "Doctor/HoSoBenhNhan";
	}

	@RequestMapping(value = { "/ChiTietLichKham" }, method = RequestMethod.GET)
	public String ChiTietLichKham(Model model) {

		return "Doctor/ChiTietLichKham";
	}

}