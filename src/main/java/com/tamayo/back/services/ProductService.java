package com.tamayo.back.services;

import java.util.Optional;

import com.tamayo.back.model.Product;
import com.tamayo.back.model.Productmodel;
import com.tamayo.back.model.Productsubcategory;
import com.tamayo.back.model.Unitmeasure;

public interface ProductService {

	public Product saveProduct(Product product);
	
	public Product editProduct(Product product);
	
	
}
