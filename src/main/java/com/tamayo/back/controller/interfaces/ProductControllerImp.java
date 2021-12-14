package com.tamayo.front.controller.implementation;

import java.io.IOException;
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

import com.tamayo.back.model.Product;
import com.tamayo.front.businessdelegate.BusinessDelegate;



@Controller
public class ProductControllerImp {
	
	private BusinessDelegate businessDelegate;

	@Autowired	
	public ProductControllerImp(BusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}
		
	@GetMapping("/prod/")
	public String indexProduct(Model model) throws IOException {
		model.addAttribute("prods", businessDelegate.productFindAll());
		return "prod/index";
	}
	
	@GetMapping("/prod/add")
    public String addProduct(Model model, @ModelAttribute("prod") Product product) {
        model.addAttribute("prod", new Product());
		model.addAttribute("prod", businessDelegate.productFindAll());
		//model.addAttribute("prodsubcats", productSubcategoryService.findAll() );
	    //model.addAttribute("prodmods", productModelService.findAll() );
	    //model.addAttribute("unitme1s", unitMeasureService.findAll() );
	    //model.addAttribute("unitme2s", unitMeasureService.findAll() );
        return "prod/add-prod";
        		
    }

	
	//NOTA: HACER EL VALIDATED
	@PostMapping("/prod/add")
    public String saveProduct(@Validated Product product, BindingResult bindingResult, Model model,
    		@RequestParam(value = "action", required = true) String action) {
        if(!action.equals("Cancel")) {
            if(bindingResult.hasErrors()) {
                model.addAttribute("prod", businessDelegate.productFindAll());
         //       model.addAttribute("prodsubcats", productSubcategoryService.findAll() );
         //       model.addAttribute("prodmods", productModelService.findAll() );
         //       model.addAttribute("unitme1s", unitMeasureService.findAll() );
         //       model.addAttribute("unitme2s", unitMeasureService.findAll() );
                return "prod/add-prod";
            }else {
            businessDelegate.productSave(product);
           }
        }
        return "redirect:/prod/";
        
    }
	
	@GetMapping("prod/del{id}")
	public String deleteProduct(@PathVariable("id") Integer id, Model model) {
		Product prod = businessDelegate.productFindById(id);
		businessDelegate.productDelete(prod.getProductid());
		return "redirect:/prod/";
	}
	
	@GetMapping("/prod/edit/{id}")
	public String editProduct(@PathVariable("id") Integer id, Model model) {
		Product prod = businessDelegate.productFindById(id);
		if (prod == null) 
			throw new IllegalArgumentException("Invalid prod Id:" + id);
		model.addAttribute("prod", prod);
		//model.addAttribute("unitme1s", unitMeasureService.findAll());
		//model.addAttribute("unitme2s", unitMeasureService.findAll());
		//model.addAttribute("prodsubcats", productSubcategoryService.findAll());
		//model.addAttribute("prodmods", productModelService.findAll());
		return "prod/update-prod";
	}
	
	//NOTA: HACER EL VALIDATED
	@PostMapping("prod/edit/{id}")
	public String updateProduct(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, 
			 @Validated Product product, BindingResult bindingResult, Model model) {
		if(action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("prod", product);
			//	model.addAttribute("unitme1s", unitMeasureService.findAll());
			//  model.addAttribute("unitme2s", unitMeasureService.findAll());
			//	model.addAttribute("prodsubcats", productSubcategoryService.findAll());
			//	model.addAttribute("prodmods", productModelService.findAll());	
				return "prod/update-prod";
			}
			businessDelegate.productEdit(product);
		}
		return "redirect:/prod/";
		
	}
	
}