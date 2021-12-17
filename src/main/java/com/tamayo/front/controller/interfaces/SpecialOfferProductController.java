package com.tamayo.front.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tamayo.front.model.Specialofferproduct;



public interface SpecialOfferProductController {
	public String indexSpecialOfferProduct(Model model);
	public String addSpecialOfferProduct(Model model);
	public String saveSpecialOfferProduct(@ModelAttribute("SpecialOfferProduct") @Validated Specialofferproduct sop, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action);
	public String deleteSpecialOfferProduct(@PathVariable("id") Integer id,@PathVariable("id2") Integer id2, Model model);
	
	public String editSpecialOfferProduct(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2, Model model);
	public String updateSpecialOfferProduct(@PathVariable("id") Integer id,@PathVariable("id2") Integer id2, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("specioffprod") @Validated Specialofferproduct specialOfferProduct, BindingResult bindingResult, Model model);
}
