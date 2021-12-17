package com.tamayo.back.services;

import java.sql.Timestamp;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamayo.back.model.Location;
import com.tamayo.back.model.Productcategory;
import com.tamayo.back.model.Productsubcategory;
import com.tamayo.back.repositories.ProductSubCategoryRepository;

@Service
public class ProductSubcategoryServiceImpl {
	
	private ProductSubCategoryRepository productSubcategoryRepository;

	
	public ProductSubcategoryServiceImpl(ProductSubCategoryRepository productSubcategoryRepository) {
		this.productSubcategoryRepository = productSubcategoryRepository;
	}
	
	public Productsubcategory saveProductSubcategory(Productsubcategory productSubcategory) {
		return productSubcategoryRepository.save(productSubcategory);
	}
	
	public Iterable<Productsubcategory> saveAll(Iterable<Productsubcategory> prosubcats){
		for(Productsubcategory x:prosubcats) {
			saveProductSubcategory(x);
		}
		return prosubcats;
	}
	
	public Optional<Productsubcategory> findById(Integer id) {
		return productSubcategoryRepository.findById(id);
	}
	
	public boolean existsById(Integer id) {
		return productSubcategoryRepository.existsById(id);
	}
	
	public Iterable<Productsubcategory> findAll() {
		return productSubcategoryRepository.findAll();
	}
	
	public Iterable<Productsubcategory> findAllById(Iterable<Productsubcategory> ids) {
		return null;
	}
	
	public long count() {
		return productSubcategoryRepository.count();
	}
	
	public void deleteById(Integer id) {
		productSubcategoryRepository.deleteById(id);
	}
	
	public void delete(Productsubcategory prodsubcat) {
		deleteById(prodsubcat.getProductsubcategoryid());
	}
	
	public void deleteAll(Iterable<Productsubcategory> prodsubcats) {
		productSubcategoryRepository.deleteAll(prodsubcats);
	}
	
	public void deleteAll() {
		productSubcategoryRepository.deleteAll();
	}
	
	public void editProductsubcategory(Integer id, Productcategory pc, String name) {
		Productsubcategory psc = findById(id).get();
		psc.setModifieddate(new Timestamp(System.currentTimeMillis()));
		psc.setName(name);
		psc.setProductcategory(pc);
		productSubcategoryRepository.save(psc);
	}

	
	
}