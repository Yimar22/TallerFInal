package com.tamayo.back.restController.implementation;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamayo.back.model.Product;
import com.tamayo.back.model.Salesorderdetail;
import com.tamayo.back.services.ProductService;
import com.tamayo.back.services.SalesOrderDetailService;

@RestController
@RequestMapping("/api-rest/saleorderdet")
public class SalesOrderDetailRestController {
	
private SalesOrderDetailService sodService;
	
	public SalesOrderDetailRestController(SalesOrderDetailService sodService) {
		this.sodService = sodService;
	}
	
	@GetMapping("/saleorderdet/")
	public Iterable<Salesorderdetail> indexSOD() {
		return sodService.findAll();
	}
	
	@GetMapping("/saleorderdet/add")
    public Salesorderdetail addSOD(@RequestBody Salesorderdetail sod )  {

		return sodService.saveSaleOrderDetail(sod);
        		
    }
	
	
	@DeleteMapping("/saleorderdet/delete")
	public void deleteSOD(@RequestBody Salesorderdetail sod)  {
		sodService.delete(sod);
	}

	
	@PutMapping("/saleorderdet/")
	public void updateSOD(@RequestBody Salesorderdetail sod) {
		sodService.editSaleOrderDetail(sod);
	}
	
	@GetMapping("/saleorderdet/")
	public Salesorderdetail findByIdSOD(@PathVariable("id") Integer id) {
		return sodService.findById(id);
	}
}
