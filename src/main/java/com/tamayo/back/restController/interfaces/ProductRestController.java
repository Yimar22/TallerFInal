package com.tamayo.back.restController.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tamayo.back.model.Product;
import com.tamayo.back.services.ProductService;

public interface ProductRestController {
	

	public Iterable<Product> indexProduct(Model model) ;
	

    public Product addProduct(@RequestBody Product product )  ;
	
	

	public void deleteProduct(@RequestBody Product product)  ;
	
	
	public void updateProduct(@RequestBody Product product) ;
}
