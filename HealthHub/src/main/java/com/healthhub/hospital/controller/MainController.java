package com.healthhub.hospital.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;


	@GetMapping({ "/", "/index" })
	public String index(Model model) {

		return "User/index";
	}

}