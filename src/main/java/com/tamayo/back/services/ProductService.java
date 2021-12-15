package com.tamayo.back.services;


import java.util.Optional;

import com.tamayo.back.model.Product;
import com.tamayo.back.model.Productmodel;
import com.tamayo.back.model.Productsubcategory;
import com.tamayo.back.model.Unitmeasure;


public interface ProductService {

public Product saveProduct(Product product);
	
	public Product editProduct(Product product);
	
	boolean existsById(Integer productId);
	
	public Iterable<Product> findAll();	

	public Optional<Product> findById(long id);
	
	public void delete(Product product);

	public Iterable<Product> findAllByProductmodel(Productmodel productModel);
	
	public Iterable<Product> findAllByProductsubcategory(Productsubcategory productSubcategory);
	
	public Iterable<Product> findAllByUnitmeasure1(Unitmeasure unitmeasure);
	
	public Iterable<Product> findAllByUnitmeasure2(Unitmeasure unitmeasure);
	
	
	
}
