package com.tamayo.back.daos;

import java.util.List;

import com.tamayo.back.model.Product;
import com.tamayo.back.model.Productmodel;


public interface ProductDao {
	public void save(Product entity);
	public void delete(Product entity);
	public void edit(Product entity);
	public Product findById(Integer id);
	public List<Product> findByProductNumber(String productnumber);
	public List<Product> findAll();
}
