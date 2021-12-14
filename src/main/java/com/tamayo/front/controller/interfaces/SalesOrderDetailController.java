package com.tamayo.front.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tamayo.back.model.Salesorderdetail;
import com.tamayo.back.model.SpecialofferproductPK;



public interface SalesOrderDetailController {
	public String indexSalesOrderDetail(Model model);
	public String addSalesOrderDetail(Model model);
	public String saveSalesOrderDetail(@ModelAttribute("salesOrderDetail") @Validated Salesorderdetail sod, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action);
	public String deleteSalesOrderDetail(@PathVariable("id1")Integer id1, @PathVariable("id2") Integer id2, Model model);
	public String editSalesOrderDetail(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2, Model model);
	public String updateSalesOrderDetail(@PathVariable("id1") Integer id1, @PathVariable("id2")Integer id2, @RequestParam(value = "action", required = true)String action, @ModelAttribute("saleorderdet") 
	@Validated Salesorderdetail salesorderdetails, BindingResult bindingResult, Model model);
}
