package com.tamayo.back.daos;

import java.util.List;

import com.tamayo.back.model.Product;
import com.tamayo.back.model.Productmodel;


public interface ProductDao {
	public void Save(Product entity);
	public void Delete(Product entity);
	public void Edit(Product entity);
	public Product findById(Integer id);
	public List<Product> findByProductNumber(String productnumber);
	public List<Product> findAll();
}
