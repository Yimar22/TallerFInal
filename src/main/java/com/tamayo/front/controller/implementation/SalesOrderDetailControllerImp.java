package com.tamayo.front.controller.implementation;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.tamayo.front.businessdelegate.BusinessDelegate;
import com.tamayo.front.controller.interfaces.SalesOrderDetailController;
import com.tamayo.front.model.Salesorderdetail;


@Controller
@RequestMapping("/salesorderdetails")
public class SalesOrderDetailControllerImp implements SalesOrderDetailController{
	
	private ArrayList<String> logicalOperands;
	
	@Autowired
	private BusinessDelegate businessDelegate;
	
	
	public SalesOrderDetailControllerImp() {
		logicalOperands = new ArrayList<>();
		logicalOperands.add("AND");
		logicalOperands.add("OR");
		logicalOperands.add("XOR");
		logicalOperands.add("NOT");
		logicalOperands.add("NOR");
		logicalOperands.add("NAND");
	}
	
	
	@GetMapping("/saleorderdet/")
	public String indexSalesOrderDetail(Model model) {
		model.addAttribute("saleorderdets", businessDelegate.salesOrderDetailFindAll());
		return "saleorderdet/index";
	}
	
	@GetMapping("/add")
	public String addSalesOrderDetail(Model model, @ModelAttribute("salesorderdetail") Salesorderdetail salesorderdetail) {
		
		return "saleorderdetails/add-saleorderdetail";
	}
	
	//NOTA: HACER EL VALIDATED
	@PostMapping("/saleorderdet/add")
	public String saveSalesOrderDetail( @Validated Salesorderdetail salesOrderDetail, BindingResult result, Model model, 
			@RequestParam(value="action", required=true) String action) {
		if(!action.equals("Cancel")) {
			if(result.hasErrors()) {
				//model.addAttribute("saleorderdet", businessDelegate.salesOrderDetailFindAll());
				model.addAttribute("prods", businessDelegate.productFindAll());
				model.addAttribute("specioffs", businessDelegate.specialofferFindAll());
				return "saleorderdet/add-saleorderdet";
			}else {
				businessDelegate.salesOrderDetailSave(salesOrderDetail);
			}
		}
		
		return "redirect:/saleorderdet/";
	}
	
	@GetMapping("/saleorderdet/del/{id1}&{id2}")
	public String deleteSalesOrderDetail(@PathVariable("id") Integer id,@PathVariable("id2") Integer id2, Model model) {
		
		return "saleorderdet/index";
	}
	
	@PostMapping("/saleorderdet/edit/{id1}&{id2}")
	public String editSalesOrderDetail(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2, Model model) {
		
		return "saleorderdet/update-saleorderdet";
	}
	
	//NOTA: HACER EL VALIDATED
	@PostMapping("/saleorderdet/edit/{id1}&{id1}")
	public String updateSalesOrderDetail(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2, 
			@RequestParam(value = "action", required = true)String action,
			@Validated Salesorderdetail salesorderdetails, BindingResult bindingResult, Model model) {
		
		return "redirect:/saleorderdet/";
	}

}
