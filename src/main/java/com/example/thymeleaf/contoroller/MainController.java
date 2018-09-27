package com.example.thymeleaf.contoroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.thymeleaf.form.PersonForm;
import com.example.thymeleaf.model.Person;

@Controller
public class MainController {
	
	private static List<Person> persons = new ArrayList<Person>();
	
	static {
		persons.add(new Person("Bill", "Gates"));
		persons.add(new Person("Steve", "Jobss"));
	}
	
	// inject via application.properties
	@Value("${welcome.message}")
	private String message;
	
	@Value("${error.message}")
	private String errorMessage;
	
	
	@RequestMapping(value= {"/", "/index"}, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("message", message);
		return "index";
	}
	
	@RequestMapping(value= {"/personList"}, method = RequestMethod.GET)
	public String personList(Model model) {
		model.addAttribute("persons", persons);
		
		return "personList";
	}
	
	@RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
	public String showAddPersonPage(Model model) {
		System.out.println("hoge");
		
		PersonForm personForm = new PersonForm();
		model.addAttribute("personForm", personForm);
		
		return "addPerson";
	}
	
	@RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
	public String savePerson(Model model, @ModelAttribute("personForm") PersonForm personForm) {
		System.out.println("hogehoge");
		String firstName = personForm.getFirstName();
		String lastName = personForm.getLastName();
		
		if(!StringUtils.isEmpty(firstName) && !StringUtils.isEmpty(lastName)) {
			Person newPerson = new Person(firstName, lastName);
			persons.add(newPerson);
			
			return "redirect:/personList";
		}
		
		model.addAttribute("errorMessage", errorMessage);
		return "addPerson";
	}

}
