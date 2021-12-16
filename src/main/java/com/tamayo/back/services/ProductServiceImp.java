package com.tamayo.back.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamayo.back.daos.ProductDao;
import com.tamayo.back.model.Product;
import com.tamayo.back.repositories.ProductModelRepository;
import com.tamayo.back.repositories.ProductRepository;
import com.tamayo.back.repositories.ProductSubCategoryRepository;
import com.tamayo.back.repositories.UnitMeasureRepository1;
import com.tamayo.back.model.Product;
import com.tamayo.back.model.Productmodel;
import com.tamayo.back.model.Productsubcategory;
import com.tamayo.back.model.Unitmeasure;

import lombok.extern.java.Log;

@Log
@Service
public class ProductServiceImp implements ProductService{

	
	private ProductDao productDao;
	private ProductSubCategoryRepository productSubcategoryRepository;
	private ProductRepository productRepository;
	private UnitMeasureRepository1 unitMeasureRepository;
	private ProductModelRepository productModelRepository;
	
	
	@Autowired
	public ProductServiceImp(ProductDao productDao, ProductSubCategoryRepository productSubcategoryRepository, ProductRepository productRepository, UnitMeasureRepository1 unitMeasureRepository, ProductModelRepository productModelRepository) {
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
		productDao.save(product);
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
		productDao.edit(product);
		return product;
	}
	
	@Override
	public boolean existsById(Integer productId) {
		return productRepository.existsById(productId);
	}
	
	@Override
	public Iterable<Product> findAll() {
		return productDao.findAll();
	}
	
	@Override
	public Product findById(Integer id) {
		return productDao.findById(id);
	}
	
	@Override
	public void delete(Product prod) {
		productDao.delete(prod);
	}


	@Override
	public Iterable<Product> findAllByProductmodel(Productmodel productModel) {
		return productRepository.findAllByProductmodel(productModel);
		
	}


	@Override
	public Iterable<Product> findAllByProductsubcategory(Productsubcategory productSubcategory) {
		return productRepository.findAllByProductsubcategory(productSubcategory);
		
	}


	@Override
	public Iterable<Product> findAllByUnitmeasure1(Unitmeasure unitmeasure) {
		return productRepository.findAllByUnitmeasure1(unitmeasure);
	}


	@Override
	public Iterable<Product> findAllByUnitmeasure2(Unitmeasure unitmeasure) {
		return productRepository.findAllByUnitmeasure2(unitmeasure);

	}
		
}
