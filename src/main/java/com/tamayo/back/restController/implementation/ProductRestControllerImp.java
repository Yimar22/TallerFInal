package com.tamayo.back.restController.implementation;

import org.springframework.ui.Model;
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
	
	@GetMapping("/")
	public Iterable<Product> indexProduct(Model model) {
		return productServ.findAll();
	}
	
	@GetMapping("/prod/add")
    public Product addProduct(@RequestBody Product product )  {
		System.out.println(product.getName());
		return productServ.saveProduct(product);
        		
    }
	
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@RequestBody Product product)  {
		productServ.delete(product);
	}

	
	@PutMapping("/{instid}")
	public void updateProduct(@RequestBody Product product) {
		productServ.editProduct(product);
	}

	
}
