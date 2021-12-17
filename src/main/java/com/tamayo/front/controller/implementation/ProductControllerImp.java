package com.tamayo.front.controller.implementation;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
import com.tamayo.front.controller.interfaces.ProductController;
import com.tamayo.front.model.Product;



@Controller
@RequestMapping("/products")
public class ProductControllerImp implements ProductController{
	
	private ArrayList<String> logicalOperands;
	
	@Autowired
	private BusinessDelegate businessDelegate;
	
	public ProductControllerImp() {
		logicalOperands = new ArrayList<>();
		logicalOperands.add("AND");
		logicalOperands.add("OR");
		logicalOperands.add("XOR");
		logicalOperands.add("NOT");
		logicalOperands.add("NOR");
		logicalOperands.add("NAND");
	}
		
	@GetMapping
	public String indexProduct(Model model) {
		model.addAttribute("products", businessDelegate.productFindAll());
		return "/products/index";
	}
	
	@GetMapping("/add")
    public String addProduct(Model model, @ModelAttribute("product") Product product) {
		model.addAttribute("subcategories", businessDelegate.findAllProductsubcategories());
	    model.addAttribute("unitme1s", businessDelegate.findAllUnitMeasures() );
        return "/products/add-product";		
    }

	@PostMapping("/add")
    public String saveProduct(@ModelAttribute("product") @Validated Product product, BindingResult bindingResult, Model model,
    		@RequestParam(value = "action", required = true) String action) {
        if(!action.equals("Cancel")) {
            if(bindingResult.hasErrors()) {
                model.addAttribute("product", businessDelegate.productFindAll());
                model.addAttribute("prodsubcategories", businessDelegate.findAllProductsubcategories() );
                model.addAttribute("unitme1s", businessDelegate.findAllUnitMeasures() );
                return "prod/add-prod";
            }else {
            businessDelegate.productSave(product);
           }
        }
        return "redirect:/products/";
        
    }
	
	@GetMapping("/del/{id}")
	public String deleteProduct(@PathVariable("id") Integer id, Model model) {
		Product prod = businessDelegate.productFindById(id);
		businessDelegate.productDelete(prod);
		return "redirect:/products/";
	}
	
	@GetMapping("/edit/{id}")
	public String editProduct(@PathVariable("id") Integer id, Model model) {
		Product prod = businessDelegate.productFindById(id);
		model.addAttribute("product", prod);
		model.addAttribute("productsubcategories", businessDelegate.findAllProductsubcategories());
		model.addAttribute("unitme1s", businessDelegate.findAllUnitMeasures());
		return "products/update-product";
	}
	
	//NOTA: HACER EL VALIDATED
	@PostMapping("/edit/{id}")
	public String updateProduct(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, 
			 @Validated Product product, BindingResult bindingResult, Model model) {
		if(action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("productsubcategories", businessDelegate.findAllProductsubcategories());
				model.addAttribute("unitme1s", businessDelegate.findAllUnitMeasures());
				return "products/update-product";
			}
			businessDelegate.productEdit(product);
		}
		return "redirect:/products/";
		
	}
	
}