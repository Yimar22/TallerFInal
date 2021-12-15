package com.tamayo.back.restController.interfaces;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tamayo.back.model.Product;

public interface ProductRestController {
	

	public Iterable<Product> indexProduct( ) ;
	

    public Product addProduct(@RequestBody Product product )  ;
	

	public void deleteProduct(@RequestBody Product product)  ;
	
	
	public void updateProduct(@RequestBody Product product) ;
	
	public Product findByIdProduct(@PathVariable("id") Integer id);
}
