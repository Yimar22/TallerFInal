package com.tamayo.back.services;

import java.util.Optional;

import com.tamayo.back.model.Productsubcategory;

public interface ProductSubCategoryService {

	public Productsubcategory saveProductSubcategory(Productsubcategory productSubcategory);
	public Productsubcategory updateProductSubcategory(Productsubcategory productsubcategory);
	public boolean existsById(Integer id);
	public Iterable<Productsubcategory> findAll();
	public Productsubcategory saveStateprovince(Productsubcategory prodsubcat);
	public Optional<Productsubcategory> findById(Integer id);
	public void delete(Productsubcategory prodsubcat);
}
