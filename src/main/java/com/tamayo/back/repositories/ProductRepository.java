package com.tamayo.back.repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.tamayo.back.model.Product;
import com.tamayo.back.model.Productcategory;
import com.tamayo.back.model.Productmodel;
import com.tamayo.back.model.Productsubcategory;
import com.tamayo.back.model.Unitmeasure;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{

public Iterable<Product> findAllByProductmodel(Productmodel productModel);
	
	public Iterable<Product> findAllByProductsubcategory(Productsubcategory productSubcategory);
	
	public Iterable<Product> findAllByUnitmeasure1(Unitmeasure unitMeasure);
	
	public Iterable<Product> findAllByUnitmeasure2(Unitmeasure unitMeasure);

	
}
