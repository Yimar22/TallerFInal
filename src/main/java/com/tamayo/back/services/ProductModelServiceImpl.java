package com.tamayo.back.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamayo.back.model.Productmodel;
import com.tamayo.back.repositories.ProductModelRepository;

@Service
public class ProductModelServiceImpl implements ProductModelService {

	private ProductModelRepository productModelRepository;

	@Autowired
	public ProductModelServiceImpl(ProductModelRepository productModelRepository) {
		super();
		this.productModelRepository = productModelRepository;
	}
	
	@Override
	public Productmodel saveProductModel(Productmodel productModel) {
		if(productModel == null)
			return null;
		return productModelRepository.save(productModel);
	}
	
	@Override
	@Transactional
	public Productmodel updateProductModel(Productmodel productModel) {
		if(productModel == null)
			return null;
		Productmodel pm = productModelRepository.findById(productModel.getProductmodelid()).get();
		pm.setName(productModel.getName());
		pm.setCatalogdescription(productModel.getCatalogdescription());
		pm.setInstructions(productModel.getInstructions());
		pm.setRowguid(productModel.getRowguid());
		pm.setModifieddate(productModel.getModifieddate());
		return productModel;
	}
	
	@Override
	public boolean existsById(Integer id) {
		return productModelRepository.existsById(id);
	}

	@Override
	public Iterable<Productmodel> findAll() {
		return productModelRepository.findAll();
	}


	@Override
	public Optional<Productmodel> findById(Integer id) {
		return productModelRepository.findById(id);
	}

	@Override
	public void delete(Productmodel prodmod) {
		productModelRepository.delete(prodmod);
	}
	
}