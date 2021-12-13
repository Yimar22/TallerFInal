package com.tamayo.back.controller.implementation;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tamayo.back.controller.interfaces.UserLoginController;
import com.tamayo.model.User;

@Controller
public class UserLoginControllerImp implements UserLoginController{
	
	@Override
	@GetMapping("/login")
	public String customLogin(Model model) {
		model.addAttribute("user", new User());
		return "custom-login";
	}
	
	@Override
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@Override
	@GetMapping("/access-denied")
	public String accessDeniedPage(@Param(value = "url") String url, Model model) {
		if(url == null) {
			url = "/";
		}
		model.addAttribute("url", url);
		return "denied";
	}
}
