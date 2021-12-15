package com.tamayo.front.businessdelegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tamayo.front.model.*;

@Component
public class BusinessDelegateImp implements BusinessDelegate {
	
	public static final String REST_URL = "http://localhost:8080/api";
	private final static String WORK_ORDER_URL = REST_URL+"/workorders/";
	private final static String WORK_ORDER_ROUTING_URL = REST_URL+"/workorderroutings/";

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
	public Salesorderdetail salesOrderDetailFindById(Integer id) {
		String url = REST_URL + "salesOrderDetail/show/" + id;
		
		Salesorderdetail salesOrderDetail = restTemplate.getForObject(url, Salesorderdetail.class); 
		
		return salesOrderDetail;
	}

	@Override
	public void salesOrderDetailDelete(Salesorderdetail id) {
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
		String url = REST_URL + "specialofferproduct/show/" + id;
		
		Specialofferproduct specialofferproduct = restTemplate.getForObject(url, Specialofferproduct.class); 
		
		return specialofferproduct;
	}

	@Override
	public void specialofferproductDelete(Specialofferproduct id) {
		String url = REST_URL + "specialofferproduct/delete/" + id;
		restTemplate.delete(url);
		
	}

	//------------------------------------------------------------------
	//							Specialoffer
	//------------------------------------------------------------------

	@Override
	public List<Specialoffer> specialofferFindAll() {
		String url = REST_URL + "specialoffer/";
		Specialoffer[] specialoffer = restTemplate.getForObject(url, Specialoffer[].class);
		List<Specialoffer> result = Arrays.asList(specialoffer);
		return result;
	}

	@Override
	public void specialofferSave(Specialoffer specialoffer) {
		String url = REST_URL + "/specialoffer/save";
		restTemplate.postForObject(url, specialoffer, Specialoffer.class);
	}

	@Override
	public void specialofferEdit(Specialoffer specialoffer) {
		String url = REST_URL + "specialoffer/edit";
		restTemplate.put(url, specialoffer, Specialoffer.class);
		
	}

	@Override
	public Specialoffer specialofferFindById(Integer id) {
		String url = REST_URL + "specialoffer/show/" + id;
		
		Specialoffer specialoffer = restTemplate.getForObject(url, Specialoffer.class); 
		
		return specialoffer;
	}

	@Override
	public void specialofferDelete(Specialoffer id) {
		String url = REST_URL + "specialoffer/delete/" + id;
		restTemplate.delete(url);
	}
	
	//------------------------------------------------------------------
	//							Workorder
	//------------------------------------------------------------------

	@Override
	public Workorder workorder_get(Integer id) {
		// TODO Auto-generated method stub
		return restTemplate.getForObject(WORK_ORDER_URL+id, Workorder.class);
	}


	@Override
	public List<Workorder> workorder_findAllByProduct(Integer id) {
		Workorder[] array = restTemplate.getForObject(WORK_ORDER_URL+"search/findAllByProduct?product="+id, Workorder[].class);
		return Arrays.asList(array);
	}


	@Override
	public List<Workorder> workorder_findAll() {
		Workorder[] array = restTemplate.getForObject(WORK_ORDER_URL, Workorder[].class);
		return Arrays.asList(array);
	}


	@Override
	public Workorder workorder_save(Workorder workorder) {
		HttpEntity<Workorder> request = new HttpEntity<>(workorder);
		return restTemplate.postForObject(WORK_ORDER_URL, request, Workorder.class);
	}


	@Override
	public void workorder_delete(Workorder workorder) {
		restTemplate.delete(WORK_ORDER_URL+workorder.getWorkorderid());
		
	}


	@Override
	public Workorder workorder_findById(Integer id) {
		return restTemplate.getForObject(WORK_ORDER_URL+id, Workorder.class);
	}


	@Override
	public void editWorkorder(Workorder workorder) {
		restTemplate.put(WORK_ORDER_URL, workorder, Workorder.class);
		
	}

	//------------------------------------------------------------------
	//							Workorderrouting
	//------------------------------------------------------------------

	@Override
	public Workorderrouting workorderrouting_get(Integer id) {
		return restTemplate.getForObject(WORK_ORDER_ROUTING_URL, Workorderrouting.class);
	}


	@Override
	public List<Workorderrouting> workorderrouting_findAllByLocation(Integer id) {
		Workorderrouting[] array = restTemplate.getForObject(WORK_ORDER_URL+"search/findAllByLocation?location="+id, Workorderrouting[].class);
		return Arrays.asList(array);
	}


	@Override
	public List<Workorderrouting> workorderrouting_findAll() {
		Workorderrouting[] array = restTemplate.getForObject(WORK_ORDER_URL, Workorderrouting[].class);
		return Arrays.asList(array);
	}


	@Override
	public Workorderrouting workorderrouting_save(Workorderrouting workorderrouting) {
		HttpEntity<Workorderrouting> request = new HttpEntity<>(workorderrouting);
		return restTemplate.postForObject(WORK_ORDER_ROUTING_URL, request, Workorderrouting.class);
	}


	@Override
	public void workorderrouting_delete(Workorderrouting workorderrouting) {
		restTemplate.delete(WORK_ORDER_ROUTING_URL+workorderrouting.getId());
	}


	@Override
	public Workorderrouting workorderrouting_findById(Integer id) {
		return restTemplate.getForObject(WORK_ORDER_ROUTING_URL+id, Workorderrouting.class);
	}


	@Override
	public void editWorkorderrouting(Workorderrouting workorderrouting) {
		restTemplate.put(WORK_ORDER_ROUTING_URL, workorderrouting, Workorderrouting.class);
	}
	

	
	

}
