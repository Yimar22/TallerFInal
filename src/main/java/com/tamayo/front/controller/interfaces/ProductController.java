package com.tamayo.front.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tamayo.front.model.Product;






public interface ProductController {
	public String indexProduct(Model model);
	public String addProduct(Model model, @ModelAttribute("product") Product product);
	public String saveProduct(@Validated Product prod, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action);
	public String deleteProduct(@PathVariable("id") Integer id, Model model);
	public String editProduct(@PathVariable("id") Integer id, Model model);
	public String updateProduct(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action,
			 @Validated Product prod, BindingResult bindingResult, Model model);
}
