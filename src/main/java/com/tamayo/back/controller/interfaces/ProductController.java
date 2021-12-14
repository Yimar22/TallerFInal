package com.tamayo.back.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tamayo.back.model.Product;




public interface ProductController {
	public String indexProduct(@RequestParam(required = false, value = "id") Integer id, Model model);
	public String addProduct(Model model);
	public String saveProduct(@ModelAttribute("addr") @Validated Product prod, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action);
	public String deleteProduct(@PathVariable("id") long id, Model model);
	public String showUpdateForm(@PathVariable("id") long id, Model model);
	public String updateProduct(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("product") @Validated Product prod, BindingResult bindingResult, Model model);
}
