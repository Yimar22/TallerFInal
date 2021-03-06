package com.tamayo.front.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tamayo.front.model.Specialoffer;



public interface SpecialOfferController {
	public String indexSpecialOffer( Model model);
	public String addSpecialOffer(Model model);
	public String saveSpecialOffer(@Validated Specialoffer so, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action);
	public String deleteSpecialOffer(@PathVariable("id") Integer id, Model model);


	@GetMapping("/specialOffer/edit/{id}")
	public String editSpecialOffer(@PathVariable("id") Integer id, Model model);

	@PostMapping("/specialOffer/edit/{id}")
	public String updateSpecialOffer(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("specialOffer") @Validated Specialoffer specialOffer, BindingResult bindingResult, Model model);
}
