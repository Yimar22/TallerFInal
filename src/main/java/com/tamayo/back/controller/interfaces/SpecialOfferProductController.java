package com.tamayo.back.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tamayo.model.Specialofferproduct;
import com.tamayo.model.SpecialofferproductPK;

public interface SpecialOfferProductController {
	public String indexSpecialOfferProduct(@RequestParam(required = false, value = "id") Long id, Model model);
	public String addSpecialOfferProduct(Model model);
	public String saveSpecialOfferProduct(@ModelAttribute("SpecialOfferProduct") @Validated Specialofferproduct sop, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action);
	public String deleteSpecialOfferProduct(@PathVariable("id") SpecialofferproductPK id, Model model);
	
	public String showUpdateForm(@PathVariable("id") SpecialofferproductPK id, Model model);
	public String updateSpecialOfferProduct(@PathVariable("id") SpecialofferproductPK id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("specioffprod") @Validated Specialofferproduct specialOfferProduct, BindingResult bindingResult, Model model);
}
