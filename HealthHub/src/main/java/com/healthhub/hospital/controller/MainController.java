package com.healthhub.hospital.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.healthhub.hospital.form.PersonForm;
import com.healthhub.hospital.model.Person;

@Controller
public class MainController {

	private static List<Person> persons = new ArrayList<Person>();

	static {
		persons.add(new Person("Bill", "Gates"));
		persons.add(new Person("Steve", "Jobs"));
	}

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

	@RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
	public String personList(Model model) {

		model.addAttribute("persons", persons);

		return "personList";
	}

	@RequestMapping(value = { "/Login" }, method = RequestMethod.GET)
	public String login(Model model) {

		return "User/Login";
	}


	@RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
	public String showAddPersonPage(Model model) {

		PersonForm personForm = new PersonForm();
		model.addAttribute("personForm", personForm);

		return "addPerson";
	}

	@RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
	public String savePerson(Model model, //
			@ModelAttribute("personForm") PersonForm personForm) {

		String firstName = personForm.getFirstName();
		String lastName = personForm.getLastName();

		if (firstName != null && firstName.length() > 0 //
				&& lastName != null && lastName.length() > 0) {
			Person newPerson = new Person(firstName, lastName);
			persons.add(newPerson);

			return "redirect:/personList";
		}

		model.addAttribute("errorMessage", errorMessage);
		return "addPerson";
	}

	@RequestMapping(value = { "/make_appointment" }, method = RequestMethod.GET)
	public String make_appoint(Model model) {

		return "User/make_appointment";
	}

	@RequestMapping(value = { "/DangNhap" }, method = RequestMethod.GET)
	public String Dangnhap(Model model) {

		return "User/DangNhap";
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