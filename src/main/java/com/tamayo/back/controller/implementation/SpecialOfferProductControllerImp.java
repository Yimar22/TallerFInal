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

import com.tamayo.back.controller.interfaces.SpecialOfferProductController;
import com.tamayo.model.Salesorderdetail;
import com.tamayo.model.Specialofferproduct;
import com.tamayo.model.SpecialofferproductPK;
import com.tamayo.services.ProductService;
import com.tamayo.services.SalesOrderDetailService;
import com.tamayo.services.SpecialOfferProductService;

import lombok.extern.java.Log;

@Log
@Controller
public class SpecialOfferProductControllerImp implements SpecialOfferProductController{
	
	private ProductService productService;
	private SpecialOfferProductService specialOfferProductService;
	
	public SpecialOfferProductControllerImp(ProductService productService, SpecialOfferProductService specialOfferProductService) {
		this.productService = productService;
		this.specialOfferProductService = specialOfferProductService;
	}
	
	@GetMapping("/specioffprod/")
	@Override
	public String indexSpecialOfferProduct(Long id, Model model) {
		return null;
	}	
	
	@GetMapping("/specioffprod/add")
	public String addSpecialOfferProduct(Model model) {
		model.addAttribute("prod", productService.findAll());
		model.addAttribute("specioffprod", specialOfferProductService.findAll());
		return  "specioffprod/add-specioffprod";
	}
	
	@PostMapping("/specioffprod/add")
	public String saveSpecialOfferProduct(@ModelAttribute("specioffprod") @Validated Specialofferproduct specialOfferProduct, BindingResult bindingResult, 
			Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("specioffprod", specialOfferProduct);
				model.addAttribute("prods", productService.findAll());
				return "specioffprod/add-specioffprod";
			}
			specialOfferProductService.edit(specialOfferProduct);
		}
		return "redirect:/specioffprod/";
	}
	
	@GetMapping("specioffprod/del{id}")
	public String deleteSpecialOfferProduct(@PathVariable("id") SpecialofferproductPK id, Model model) {
		Specialofferproduct sop = specialOfferProductService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid special offer product Id:" + id));
		specialOfferProductService.delete(sop);
		model.addAttribute("specioffprods", specialOfferProductService.findAll());
		return "specioffprod/update-prod";
	}
	
	@GetMapping("/specioffprod/edit/{id}")
	public String showUpdateForm(@PathVariable("id") SpecialofferproductPK id, Model model) {
		Specialofferproduct sop = specialOfferProductService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid  special offer product Id:" + id));
		model.addAttribute("specioffprod", sop);
		model.addAttribute("prods", productService.findAll());
		return "specioffprod/update-specioffprod";
	}
	
	@PostMapping("specioffprod/edit/{id}")
	public String updateSpecialOfferProduct(@PathVariable("id") SpecialofferproductPK id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("specioffprod") @Validated Specialofferproduct specialOfferProduct, BindingResult bindingResult, Model model) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("specioffprod", specialOfferProduct);
				model.addAttribute("prods", productService.findAll());
				return "specioffprod/update-specioffprod";
			}
			specialOfferProductService.edit(specialOfferProduct);
			model.addAttribute("specioffprods", specialOfferProductService.findAll());
		}
		return "redirect:/prod/";
	}

	


}
