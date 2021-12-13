package com.tamayo.back.controller.implementation;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tamayo.back.controller.interfaces.SpecialOfferController;
import com.tamayo.model.Specialoffer;
import com.tamayo.model.Specialofferproduct;
import com.tamayo.services.SpecialOfferService;
@Controller
public class SpecialOfferControllerImp implements SpecialOfferController {
	private SpecialOfferService soService;

	@Autowired	
	public SpecialOfferControllerImp(SpecialOfferService soService) {
		this.soService = soService;
	}
		
	@GetMapping("/specioff/")
	public String indexSpecialOffer(@RequestParam(required = false, value = "id") Long id, Model model) {
		if(id == null) {
			model.addAttribute("specialOffer", soService.findAll());
		}
		return "specioff/index";
	}
	
	@GetMapping("/specioff/add")
	public String addSpecialOffer(Model model) {
		model.addAttribute("specialOffer", new Specialoffer());
		return "specioff/add-specioff";
	}
		
	@PostMapping("/specioff/add")
	public String saveSpecialOffer(@ModelAttribute("specioff") @Validated Specialoffer specialOffer, 
			BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("specialOfferProduct", specialOffer);
				return "specioff/add-so";
			}
			soService.saveoffer(specialOffer);
		}
		return "redirect:/salesOrderDetail/";
	}
	@GetMapping("/specialOffer/del/{id}")
	public String deleteSpecialOffer(@PathVariable("id") Integer id, Model model) {
		Specialoffer specialOffer = soService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid special offer Id:" + id));
		soService.delete(specialOffer);
		model.addAttribute("specialOffer", soService.findAll());
		return "specialOffer/update-specialoffer";
	}


	@GetMapping("/specialOffer/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Specialoffer specialOffer = soService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid special offer Id:" + id));
		model.addAttribute("specialOffer", specialOffer);
		return "specialOffer/update-specialOffer";
	}

	@PostMapping("/specialOffer/edit/{id}")
	public String updateSpecialOffer(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("specialOffer") @Validated Specialoffer specialOffer, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("SpecialOffer", specialOffer);
				return "specialOffer/update-specialOffer";		
			}
			soService.editoffer(specialOffer);
			model.addAttribute("specialOffers", soService.findAll());
		}
		return "redirect:/specialOffer/";
	}





}
