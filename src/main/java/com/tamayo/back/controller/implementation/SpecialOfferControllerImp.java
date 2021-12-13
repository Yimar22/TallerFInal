package com.tamayo.back.controller.implementation;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tamayo.back.businessdelegate.BusinessDelegate;
import com.tamayo.back.model.Specialoffer;

@Controller
public class SpecialOfferControllerImp  {
	
	private BusinessDelegate businessDelegate;

	@Autowired	
	public SpecialOfferControllerImp(BusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}
		
	@GetMapping("/specioff/")
	public String indexSpecialOffer(Model model) {
		model.addAttribute("specialOffer", businessDelegate.specialofferFindAll());
		return "specioff/index";
	}
	
	@GetMapping("/specioff/add")
	public String addSpecialOffer(Model model) {
		model.addAttribute("specialOffer", new Specialoffer());
		return "specioff/add-specioff";
	}
	
	//NOTA: HACER EL VALIDATED
	@PostMapping("/specioff/add")
	public String saveSpecialOffer(@Validated Specialoffer specialOffer, 
			BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("product", businessDelegate.productFindAll());
				return "specioff/add-so";
			}
			businessDelegate.specialofferSave(specialOffer);
		}
		return "redirect:/salesOrderDetail/";
	}
	@GetMapping("/specialOffer/del/{id}")
	public String deleteSpecialOffer(@PathVariable("id") Integer id, Model model) {
		Specialoffer specialOffer = businessDelegate.specialofferFindById(id);
		businessDelegate.specialofferDelete(specialOffer);
		return "redirect:/salesOrderDetail/";
	}


	@GetMapping("/specialOffer/edit/{id}")
	public String editSpecialOffer(@PathVariable("id") Integer id, Model model) {
		Specialoffer specialOffer = businessDelegate.specialofferFindById(id);
		if (specialOffer == null)
			throw new IllegalArgumentException("Invalid specialOffer Id:" + id);
		
		model.addAttribute("specialOffer", specialOffer);
		model.addAttribute("product", businessDelegate.productFindAll());
		return "specialOffer/update-specialOffer";
	}


	//NOTA: HACER EL VALIDATED
	@PostMapping("/specialOffer/edit/{id}")
	public String updateSpecialOffer(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action,
			 @Validated Specialoffer specialOffer, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("SpecialOffer", specialOffer);
				model.addAttribute("product", businessDelegate.productFindAll());
				return "specialOffer/update-specialOffer";		
			}
			businessDelegate.specialofferEdit(specialOffer);
		}
		return "redirect:/specialOffer/";
	}





}
