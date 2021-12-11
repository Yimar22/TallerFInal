package com.tamayo.back.businessdelegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.tamayo.back.model.Product;
import com.tamayo.back.model.Salesorderdetail;
import com.tamayo.back.model.SalesorderdetailPK;
import com.tamayo.back.model.Specialoffer;
import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.model.SpecialofferproductPK;

public class BusinessDelegateImp implements BusinessDelegate {
	
	public static final String REST_URL = "http://localhost:8080/api";

	private RestTemplate restTemplate;
	
	public BusinessDelegateImp() {
		this.restTemplate = new RestTemplate();
	}


	//------------------------------------------------------------------
	//							PRODUCT
	//------------------------------------------------------------------
	
	@Override
	public List<Product> productFindAll() {
		String url = REST_URL + "product/";
		Product[] product = restTemplate.getForObject(url, Product[].class);
		List<Product> result = Arrays.asList(product);
		return result;
	}

	@Override
	public void productSave(Product product) {
		String url = REST_URL + "/product/save";
		restTemplate.postForObject(url, product, Product.class);
		
	}

	@Override
	public void productEdit(Product product) {
		String url = REST_URL + "product/edit";
		restTemplate.put(url, product, Product.class);
	}

	@Override
	public Product productFindById(Integer id) {
		String url = REST_URL + "product/show/" + id;
		
		Product product = restTemplate.getForObject(url, Product.class); 
		
		return product;
	}

	@Override
	public void productDelete(Integer id) {
		String url = REST_URL + "product/delete/" + id;
		restTemplate.delete(url);
	}
	

	//------------------------------------------------------------------
	//							Salesorderdetail
	//------------------------------------------------------------------
	

	@Override
	public List<Salesorderdetail> salesOrderDetailFindAll() {
		String url = REST_URL + "salesOrderDetail/";
		Salesorderdetail[] salesOrderDetail = restTemplate.getForObject(url, Salesorderdetail[].class);
		List<Salesorderdetail> result = Arrays.asList(salesOrderDetail);
		return result;
	}

	@Override
	public void salesOrderDetailSave(Salesorderdetail salesOrderDetail) {
		String url = REST_URL + "/salesOrderDetail/save";
		restTemplate.postForObject(url, salesOrderDetail, Salesorderdetail.class);
	}

	@Override
	public void salesOrderDetailEdit(Salesorderdetail salesOrderDetail) {
		String url = REST_URL + "salesOrderDetail/edit";
		restTemplate.put(url, salesOrderDetail, Salesorderdetail.class);
	}

	@Override
	public Salesorderdetail salesOrderDetailFindById(SalesorderdetailPK id) {
		String url = REST_URL + "salesOrderDetail/show/" + id;
		
		Salesorderdetail salesOrderDetail = restTemplate.getForObject(url, Salesorderdetail.class); 
		
		return salesOrderDetail;
	}

	@Override
	public void salesOrderDetailDelete(Integer id) {
		String url = REST_URL + "salesOrderDetail/delete/" + id;
		restTemplate.delete(url);
	}

	//------------------------------------------------------------------
	//							Specialofferproduct
	//------------------------------------------------------------------
	
	@Override
	public List<Specialofferproduct> specialofferproductFindAll() {
		String url = REST_URL + "specialofferproduct/";
		Specialofferproduct[] specialofferproduct = restTemplate.getForObject(url, Specialofferproduct[].class);
		List<Specialofferproduct> result = Arrays.asList(specialofferproduct);
		return result;
	}

	@Override
	public void specialofferproductSave(Specialofferproduct specialofferproduct) {
		String url = REST_URL + "/specialofferproduct/save";
		restTemplate.postForObject(url, specialofferproduct, Specialofferproduct.class);
	}

	@Override
	public void specialofferproductEdit(Specialofferproduct specialofferproduct) {
		String url = REST_URL + "specialofferproduct/edit";
		restTemplate.put(url, specialofferproduct, Specialofferproduct.class);
		
	}

	@Override
	public Specialofferproduct specialofferproductFindById(SpecialofferproductPK id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void specialofferproductDelete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	//------------------------------------------------------------------
	//							Specialoffer
	//------------------------------------------------------------------

	@Override
	public List<Specialoffer> specialofferFindAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void specialofferSave(Specialoffer specialoffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void specialofferEdit(Specialoffer specialoffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Specialoffer specialofferFindById(Specialoffer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void specialofferDelete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
