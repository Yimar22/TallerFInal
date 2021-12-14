package com.tamayo.front.controller.implementation;

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

import com.tamayo.back.model.Salesorderdetail;
import com.tamayo.back.model.SalesorderdetailPK;
import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.model.SpecialofferproductPK;
import com.tamayo.front.businessdelegate.BusinessDelegate;
import com.tamayo.front.controller.interfaces.SpecialOfferProductController;

import lombok.extern.java.Log;

@Log
@Controller
public class SpecialOfferProductControllerImp implements SpecialOfferProductController{
	
	private BusinessDelegate businessDelegate;

	
	public SpecialOfferProductControllerImp(BusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}
	
	@GetMapping("/specioffprod/")
	public String indexSpecialOfferProduct(Model model) {
		model.addAttribute("specioffprod", businessDelegate.specialofferproductFindAll());
		return "specioffprod/index";
	}	
	
	@GetMapping("/specioffprod/add")
	public String addSpecialOfferProduct(Model model) {
	//model.addAttribute("prod", businessDelegate.productFindAll());
		model.addAttribute("specioffprod", new Specialofferproduct());
		return  "specioffprod/add-specioffprod";
	}
	
	//NOTA: HACER EL VALIDATED
	@PostMapping("/specioffprod/add")
	public String saveSpecialOfferProduct(@Validated Specialofferproduct specialOfferProduct, BindingResult bindingResult, 
			Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
			//	model.addAttribute("specioffprod", specialOfferProduct);
				model.addAttribute("prods", businessDelegate.productFindAll());
				return "specioffprod/add-specioffprod";
			}
			businessDelegate.specialofferproductSave(specialOfferProduct);
		}
		return "redirect:/specioffprod/";
	}
	
	@GetMapping("specioffprod/del{id}")
	public String deleteSpecialOfferProduct(@PathVariable("id") Integer id,@PathVariable("id2") Integer id2, Model model) {
		SpecialofferproductPK sopPK = new SpecialofferproductPK();
		sopPK.setProductid(id);
		sopPK.setProductid(id2);		
		Specialofferproduct sop = businessDelegate.specialofferproductFindById(sopPK);
		businessDelegate.specialofferproductDelete(sop);
		return "specioffprod/update-prod";
	}
	
	@GetMapping("/specioffprod/edit/{id}")
	public String editSpecialOfferProduct(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2, Model model) {
		SpecialofferproductPK sopPK = new SpecialofferproductPK();
		sopPK.setProductid(id1);
		sopPK.setProductid(id2);		
		Specialofferproduct sop = businessDelegate.specialofferproductFindById(sopPK);
		if (sop == null)
			throw new IllegalArgumentException("Invalid user Id:" + id1
					);
		model.addAttribute("specioffprod", sop);
		model.addAttribute("prods", businessDelegate.productFindAll());
		return "specioffprod/update-specioffprod";
	}
	
	//NOTA: HACER EL VALIDATED
	@PostMapping("specioffprod/edit/{id}")
	public String updateSpecialOfferProduct(@PathVariable("id") Integer id,@PathVariable("id2") Integer id2, @RequestParam(value = "action", required = true) String action,
			@Validated Specialofferproduct specialOfferProduct, BindingResult bindingResult, Model model) {
		SpecialofferproductPK sopPK = new SpecialofferproductPK();
		sopPK.setProductid(id);
		sopPK.setProductid(id2);		
		Specialofferproduct sop = businessDelegate.specialofferproductFindById(sopPK);
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("specioffprod", specialOfferProduct);
				model.addAttribute("prods", businessDelegate.productFindAll());
				return "specioffprod/update-specioffprod";
			}
			specialOfferProduct.setId(sopPK);
			businessDelegate.specialofferproductEdit(specialOfferProduct);
		}
		return "redirect:/prod/";
	}

	


}
