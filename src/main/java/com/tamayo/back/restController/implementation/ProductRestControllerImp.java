package com.tamayo.back.restController.implementation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamayo.back.model.Product;
import com.tamayo.back.services.ProductService;


@RestController
@RequestMapping("/api-rest/product")
public class ProductRestControllerImp {

	
	private ProductService productServ;
	
	public ProductRestControllerImp(ProductService productServ) {
		this.productServ = productServ;
	}
	
	@GetMapping("/prod/")
	public Iterable<Product> indexProduct() {
		return productServ.findAll();
	}
	
	@GetMapping("/prod/add")
    public Product addProduct(@RequestBody Product product )  {
		System.out.println(product.getName());
		return productServ.saveProduct(product);
        		
    }
	
	
	@DeleteMapping("/prod/delete")
	public void deleteProduct(@RequestBody Product product)  {
		productServ.delete(product);
	}

	
	@PutMapping("/prod/edit")
	public void updateProduct(@RequestBody Product product) {
		productServ.editProduct(product);
	}
	
	@GetMapping("/prod/")
	public Product findByIdProduct(@PathVariable("id") Integer id) {
		return productServ.findById(id);
	}
	
}
