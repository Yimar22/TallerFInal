package com.tamayo.back.restController.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamayo.back.daos.ProductsubcategoryDao;
import com.tamayo.back.model.Productsubcategory;


@RestController
@RequestMapping("/api/productsubcategories")
public class ProductsubcategoryRestControllerImpl {

	@Autowired
	ProductsubcategoryDao productsubcategoryDao;
	
	@GetMapping
    public List<Productsubcategory> getProductsubcategories() {
        return productsubcategoryDao.getAll();
    }
	
	@GetMapping("/{id}")
	public Productsubcategory getbyId(@PathVariable("id") Integer id) {
		return productsubcategoryDao.get(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
	}
	
	@PostMapping
	public void addProductsubcategory(@RequestBody Productsubcategory productsubcategory) {
		productsubcategoryDao.save(productsubcategory);
	}
	
	@PutMapping
	public void updateProductsubcategory(@RequestBody Productsubcategory productsubcategory) {
		productsubcategoryDao.update(productsubcategory);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		productsubcategoryDao.deleteById(id);
	}
	
}
