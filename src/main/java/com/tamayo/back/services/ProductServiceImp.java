package com.tamayo.back.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamayo.back.daos.ProductDao;
import com.tamayo.back.model.Product;
import com.tamayo.back.model.Productcategory;
import com.tamayo.back.model.Productmodel;
import com.tamayo.back.model.Productsubcategory;
import com.tamayo.back.model.Unitmeasure;
import com.tamayo.back.repositories.ProductModelRepository;
import com.tamayo.back.repositories.ProductRepository;
import com.tamayo.back.repositories.ProductSubCategoryRepository;
import com.tamayo.back.repositories.UnitMeasureRepository;

import lombok.extern.java.Log;

@Log
@Service
public class ProductServiceImp implements ProductService{

	
	private ProductDao productDao;
	private ProductSubCategoryRepository productSubcategoryRepository;
	private ProductRepository productRepository;
	private UnitMeasureRepository unitMeasureRepository;
	private ProductModelRepository productModelRepository;
	
	
	@Autowired
	public ProductServiceImp(ProductDao productDao, ProductSubCategoryRepository productSubcategoryRepository, ProductRepository productRepository, UnitMeasureRepository unitMeasureRepository, ProductModelRepository productModelRepository) {
		this.productDao= productDao;
		this.productSubcategoryRepository = productSubcategoryRepository;
		this.productRepository = productRepository; 
		this.unitMeasureRepository = unitMeasureRepository;
		this.productModelRepository = productModelRepository;
	}
	
	
	@Override
	public Product saveProduct(Product product){
		if(product == null || product.getProductmodel() == null || product.getProductsubcategory() == null || product.getUnitmeasure1() == null || product.getUnitmeasure2() == null || !productModelRepository.existsById(product.getProductmodel().getProductmodelid()) || !productSubcategoryRepository.existsById(product.getProductsubcategory().getProductsubcategoryid()) || !unitMeasureRepository.existsById(product.getUnitmeasure1().getUnitmeasurecode()) || !unitMeasureRepository.existsById(product.getUnitmeasure2().getUnitmeasurecode())) {
			return null;
		}
		boolean result = product.getProductnumber() != null && product.getSellstartdate().before(product.getSellenddate()) && product.getDaystomanufacture() > 0;
		if(!result)return null;
		product = productRepository.save(product);
		productDao.Save(product);
		return product;
	}
	
	@Override
	@Transactional
	public Product editProduct(Product product) {
		if(product == null) {
			return null;
		}
		
		boolean result = product.getProductnumber() != null && product.getSellstartdate().before(product.getSellenddate()) && product.getDaystomanufacture() > 0;
		if(!result) return null;
		
		Integer productId = product.getProductid();
		if(!productRepository.existsById(productId))return null;
		
		Product existingProduct = productRepository.findById(productId).get();
		
		existingProduct.setProductnumber(product.getProductnumber());
		existingProduct.setSellstartdate(product.getSellstartdate());
		existingProduct.setSellenddate(product.getSellenddate());
		existingProduct.setDaystomanufacture(product.getDaystomanufacture());
		existingProduct.setProductmodel(product.getProductmodel());
		existingProduct.setProductsubcategory(product.getProductsubcategory());
		existingProduct.setUnitmeasure1(product.getUnitmeasure1());
		existingProduct.setUnitmeasure2(product.getUnitmeasure2());
		productDao.Save(product);
		return product;
	}
	
	
	
		
}
