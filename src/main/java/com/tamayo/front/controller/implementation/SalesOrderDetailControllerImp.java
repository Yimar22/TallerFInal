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
import com.tamayo.front.businessdelegate.BusinessDelegate;
import com.tamayo.front.controller.interfaces.SalesOrderDetailController;


@Controller
public class SalesOrderDetailControllerImp implements SalesOrderDetailController{
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
	public String addSalesOrderDetail(Model model) {
		model.addAttribute("saleorderdet", new Salesorderdetail());
		model.addAttribute("prods", businessDelegate.productFindAll());
		model.addAttribute("specioffs", businessDelegate.specialofferFindAll());
		return "saleorderdet/add-saleorderdet";
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
		SalesorderdetailPK sodPK = new SalesorderdetailPK();
		sodPK.setSalesorderid(id);
		sodPK.setSalesorderdetailid(id2);		
		Salesorderdetail sod = businessDelegate.salesOrderDetailFindById(sodPK);
		businessDelegate.salesOrderDetailDelete(sod);
		return "saleorderdet/index";
	}
	
	@PostMapping("/saleorderdet/edit/{id1}&{id2}")
	public String editSalesOrderDetail(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2, Model model) {
		SalesorderdetailPK sodPK = new SalesorderdetailPK();
		sodPK.setSalesorderid(id1);
		sodPK.setSalesorderdetailid(id2);		
		Salesorderdetail sod = businessDelegate.salesOrderDetailFindById(sodPK);
		if (sod == null)
			throw new IllegalArgumentException("Invalid user Id:" + id1
					);
		model.addAttribute("saleorderdet", sod);
		model.addAttribute("prods", businessDelegate.productFindAll());
		model.addAttribute("specioffs", businessDelegate.specialofferFindAll());
		return "saleorderdet/update-saleorderdet";
	}
	
	//NOTA: HACER EL VALIDATED
	@PostMapping("/saleorderdet/edit/{id1}&{id1}")
	public String updateSalesOrderDetail(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2, 
			@RequestParam(value = "action", required = true)String action,
			@Validated Salesorderdetail salesorderdetails, BindingResult bindingResult, Model model) {
		SalesorderdetailPK sodPK = new SalesorderdetailPK();
		sodPK.setSalesorderid(id1);
		sodPK.setSalesorderdetailid(id2);		
		
		if(action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				salesorderdetails.setId(sodPK);
				model.addAttribute("saleorderdet", salesorderdetails);
				model.addAttribute("prods", businessDelegate.productFindAll());
				model.addAttribute("specioffs", businessDelegate.specialofferFindAll());
				return "saleorderdet/update-saleorderdet";
			}
			salesorderdetails.setId(sodPK);
			businessDelegate.salesOrderDetailEdit(salesorderdetails);
		}
		return "redirect:/saleorderdet/";
	}

}
