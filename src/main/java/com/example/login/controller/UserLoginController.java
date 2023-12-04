package com.example.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.login.services.UserService;
import com.example.login.web.dto.DtoClass;


@Controller
@RequestMapping("/registration")//this will be mapped with getmapping method
public class UserLoginController {
//autowired can also be used
		private UserService userService;

		public UserLoginController(UserService userService) {
			super();
			this.userService = userService;
		}
		
		@ModelAttribute("user")
	    public  DtoClass userRegistrationDto() {
	        return new DtoClass();
	    }
         //this can be done in this format also
		//DtoClass dc= new DtoClass();
		//model.addAttribute("user",dc);
		
		@GetMapping// instead this @RequestMapping("/registration")
		public String showRegistrationForm() {
			return "registration";
		}
		
		@PostMapping
		public String registerUserAccount(@ModelAttribute("user") DtoClass registrationDto) {
			userService.save(registrationDto);
			return "redirect:/registration?success";
		}
	
}
// hum sirf userpojo ka use end m means service ka imp karta h baki hum dtouserpojo ka use karta h 
