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

import com.tamayo.back.businessdelegate.BusinessDelegate;
import com.tamayo.back.controller.interfaces.SalesOrderDetailController;
import com.tamayo.back.model.Salesorderdetail;
import com.tamayo.back.model.SalesorderdetailPK;


@Controller
public class SalesOrderDetailControllerImp{
	private BusinessDelegate businessDelegate;
	
	@Autowired
	public SalesOrderDetailControllerImp(BusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}
	
	@GetMapping("/saleorderdet/")
	public String indexSalesOrderDetail(Model model) {
		model.addAttribute("saleorderdets", businessDelegate.salesOrderDetailFindAll());
		return "saleorderdet/index";
	}
	
	@GetMapping("/saleorderdet/add")
	public String addSalesOrderDetail(Model model, @ModelAttribute("saleorderdet") Salesorderdetail salesOrderDetail) {
		model.addAttribute("saleorderdet", new Salesorderdetail());
		//model.addAttribute("prods", businessDelegate.productFindAll());
		//model.addAttribute("specioffs", businessDelegate.specialofferFindAll());
		return "saleorderdet/add-saleorderdet";
	}
	
	//NOTA: HACER EL VALIDATED
	@PostMapping("/saleorderdet/add")
	public String saveSalesOrderDetail( @Validated Salesorderdetail salesOrderDetail, BindingResult result, Model model, 
			@RequestParam(value="action", required=true) String action) {
		if(!action.equals("Cancel")) {
			if(result.hasErrors()) {
				model.addAttribute("saleorderdet", businessDelegate.salesOrderDetailFindAll());
				//model.addAttribute("prods", businessDelegate.productFindAll());
				//model.addAttribute("specioffs", businessDelegate.specialofferFindAll());
				return "saleorderdet/add-saleorderdet";
			}
		}
		
		return "redirect:/saleorderdet/";
	}
	
	@GetMapping("/saleorderdet/del/{id1}&{id2}")
	public String deleteSalesOrderDetail(@PathVariable("id") SalesorderdetailPK id, Model model) {
		Salesorderdetail sod = businessDelegate.salesOrderDetailFindById(id);
		businessDelegate.salesOrderDetailDelete(sod.getId());
		model.addAttribute("saleorderdet", businessDelegate.salesOrderDetailFindAll());
		return "saleorderdet/index";
	}
	
	@PostMapping("/saleorderdet/edit/{id1}&{id2}")
	public String showUpdateForm(@PathVariable("id1")long id1, @PathVariable("id2")long id2, Model model) {
		SalesorderdetailPK id = new SalesorderdetailPK();
		id.setSalesorderdetailid((int)id1);
		id.setSalesorderid((int)id2);
		Salesorderdetail sod = salesOrderDetailService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid user Id:" + id));
		model.addAttribute("saleorderdet", new Salesorderdetail());
		model.addAttribute("prods", productService.findAll());
		model.addAttribute("specioffs", specialOfferService.findAll());
		return "saleorderdet/update-saleorderdet";
	}
	
	@PostMapping("/saleorderdet/edit/{id1}&{id1}")
	public String updateSalesOrderDetail(@PathVariable("id1") long id1, @PathVariable("id2")long id2, @RequestParam(value = "action", required = true)String action, @ModelAttribute("saleorderdet") @Validated Salesorderdetail salesorderdetails, BindingResult bindingResult, Model model) {
		if(action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("saleorderdet", new Salesorderdetail());
				model.addAttribute("prods", productService.findAll());
				model.addAttribute("specioffs", specialOfferService.findAll());
				return "saleorderdet/update-saleorderdet";
			}
			salesOrderDetailService.editSaleOrderDetail(salesorderdetails);
			model.addAttribute("saleorderdets", salesOrderDetailService.findAll());
		}
		return "redirect:/saleorderdet/";
	}

}
