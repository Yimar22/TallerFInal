package com.tamayo.back.businessdelegate;

import java.util.List;

import com.tamayo.back.model.Product;
import com.tamayo.back.model.Salesorderdetail;
import com.tamayo.back.model.SalesorderdetailPK;
import com.tamayo.back.model.Specialoffer;
import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.model.SpecialofferproductPK;



public interface BusinessDelegate {


    // ==========================
    // PRODUCT
    // ==========================

	public List<Product> productFindAll();

	public void productSave(Product product);
	
	public void productEdit(Product product);
	
	public Product productFindById(Integer id);
	
	public void productDelete(Integer id);
	
	 // ==========================
    // SALES ORDER DETAIL
    // ==========================
	
	public List<Salesorderdetail> salesOrderDetailFindAll();

	public void salesOrderDetailSave(Salesorderdetail salesOrderDetail);
	
	public void salesOrderDetailEdit(Salesorderdetail salesOrderDetail);
	
	public Salesorderdetail salesOrderDetailFindById(SalesorderdetailPK id);
	
	public void salesOrderDetailDelete(Salesorderdetail id);
	
	
	// ==========================
    // Special offer product
    // ==========================
	
	public List<Specialofferproduct> specialofferproductFindAll();

	public void specialofferproductSave(Specialofferproduct salesOrderDetail);
	
	public void specialofferproductEdit(Specialofferproduct salesOrderDetail);
	
	public Specialofferproduct specialofferproductFindById(SpecialofferproductPK id);
	
	public void specialofferproductDelete(Specialofferproduct id);
	
	// ==========================
    // Special offer 
    // ==========================
	
	public List<Specialoffer> specialofferFindAll();

	public void specialofferSave(Specialoffer specialoffer);
	
	public void specialofferEdit(Specialoffer specialoffer);
	
	public Specialoffer specialofferFindById(Integer id);
	
	public void specialofferDelete(Specialoffer id);
}
