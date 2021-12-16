package com.tamayo.front.businessdelegate;

import java.util.List;

import com.tamayo.front.model.*;



public interface BusinessDelegate {


    // ==========================
    // PRODUCT
    // ==========================

	public List<Product> productFindAll();

	public void productSave(Product product);
	
	public void productEdit(Product product);
	
	public Product productFindById(Integer id);
	
	public void productDelete(Integer id);
	
	public Productsubcategory productsubcategory_save(Productsubcategory productsubcategory);
	
	public Unitmeasure unitmeasure1_save(Unitmeasure unitmeasure);
	
	public Unitmeasure unitmeasure2_save(Unitmeasure unitmeasure);
	 // ==========================
    // SALES ORDER DETAIL
    // ==========================
	
	public List<Salesorderdetail> salesOrderDetailFindAll();

	public void salesOrderDetailSave(Salesorderdetail salesOrderDetail);
	
	public void salesOrderDetailEdit(Salesorderdetail salesOrderDetail);
	
	public Salesorderdetail salesOrderDetailFindById(Integer id);
	
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
	
	// ==========================
	// Work order
	// ==========================
	
	Workorder workorder_get(Integer id);
	List<Workorder> workorder_findAllByProduct(Integer id);
	List<Workorder> workorder_findAll();
	Workorder workorder_save(Workorder workorder);
	void workorder_delete(Workorder workorder);
	Workorder workorder_findById(Integer id);
	void editWorkorder(Workorder workorder);
	
	// ==========================
	// Work order routing
	// ==========================
	
	Workorderrouting workorderrouting_get(Integer id);
	List<Workorderrouting> workorderrouting_findAllByLocation(Integer id);
	List<Workorderrouting> workorderrouting_findAll();
	Workorderrouting workorderrouting_save(Workorderrouting workorderrouting);
	void workorderrouting_delete(Workorderrouting workorderrouting);
	Workorderrouting workorderrouting_findById(Integer id);
	void editWorkorderrouting(Workorderrouting workorderrouting);
}
