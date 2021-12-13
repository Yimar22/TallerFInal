package com.tamayo.back.controller.interfaces;

import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;

public interface UserLoginController {
	public String customLogin(Model model);
	public String index();
	public String accessDeniedPage(@Param(value = "url") String url, Model model);
}
