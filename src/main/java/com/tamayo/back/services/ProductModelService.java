package com.tamayo.back.services;

import java.util.Optional;


import com.tamayo.back.model.Productmodel;


public interface ProductModelService {

	
	public Productmodel updateProductModel(Productmodel productModel);
	
	public boolean existsById(Integer id);
	
	public Iterable<Productmodel> findAll() ;
	
	public Productmodel saveProductModel(Productmodel prodmod);
	
	public Optional<Productmodel> findById(Integer id);
	
	public void delete(Productmodel prodmod);
	
}
