package com.tamayo.front.controller.implementation;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserLoginControllerImp {
	
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	@GetMapping("/")
	public String index() {
		return "/index";
	}
	
	
}
