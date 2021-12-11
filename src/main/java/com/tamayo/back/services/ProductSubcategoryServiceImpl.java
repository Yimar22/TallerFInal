package com.tamayo.back.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamayo.back.model.Productsubcategory;
import com.tamayo.back.repositories.ProductSubCategoryRepository;

@Service
public class ProductSubcategoryServiceImpl  implements ProductSubCategoryService {
	
	private ProductSubCategoryRepository productSubcategoryRepository;

	@Autowired
	public ProductSubcategoryServiceImpl(ProductSubCategoryRepository productSubcategoryRepository) {
		super();
		this.productSubcategoryRepository = productSubcategoryRepository;
	}
	
	@Override
	public Productsubcategory saveProductSubcategory(Productsubcategory productSubcategory) {
		if(productSubcategory == null)
			return null;
		return productSubcategoryRepository.save(productSubcategory);
	}
	
	@Override
	@Transactional
	public Productsubcategory updateProductSubcategory(Productsubcategory productsubcategory) {
		if(productsubcategory == null)
			return null;
		Productsubcategory ps = productSubcategoryRepository.findById(productsubcategory.getProductsubcategoryid()).get();
		ps.setName(productsubcategory.getName());
		ps.setRowguid(productsubcategory.getRowguid());
		ps.setModifieddate(productsubcategory.getModifieddate());
		return productsubcategory;
	}
	
	@Override
	public boolean existsById(Integer id) {
		return productSubcategoryRepository.existsById(id);
	}

	@Override
	public Iterable<Productsubcategory> findAll() {
		return productSubcategoryRepository.findAll();
	}

	@Override
	public Productsubcategory saveStateprovince(Productsubcategory prodsubcat) {
		return productSubcategoryRepository.save(prodsubcat);
	}

	@Override
	public Optional<Productsubcategory> findById(Integer id) {
		return productSubcategoryRepository.findById(id);
	}

	@Override
	public void delete(Productsubcategory prodsubcat) {
		productSubcategoryRepository.delete(prodsubcat);
	}
	
}