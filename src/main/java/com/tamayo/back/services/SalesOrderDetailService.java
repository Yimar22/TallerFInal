package com.tamayo.back.services;

import java.util.Optional;

import com.tamayo.back.model.PurchaseorderdetailPK;
import com.tamayo.back.model.Salesorderdetail;
import com.tamayo.back.model.SalesorderdetailPK;

public interface SalesOrderDetailService {

	public Salesorderdetail saveSaleOrderDetail(Salesorderdetail salesOrderDetail);
	public Salesorderdetail editSaleOrderDetail(Salesorderdetail salesOrderDetail);
	boolean existsById(Integer salesOrderDetailId);

	public Iterable<Salesorderdetail> findAll();
	
	public Optional<Salesorderdetail> findById(Integer id);
	
	public void delete(Salesorderdetail salesOrderDetail);
	
	
}
