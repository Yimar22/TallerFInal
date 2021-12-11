package com.tamayo.back.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamayo.back.daos.SalesorderdetailDao;
import com.tamayo.back.model.Product;
import com.tamayo.back.model.Productcategory;
import com.tamayo.back.model.PurchaseorderdetailPK;
import com.tamayo.back.model.Salesorderdetail;
import com.tamayo.back.model.SalesorderdetailPK;
import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.repositories.ProductRepository;
import com.tamayo.back.repositories.SalesOrderDetailRepository;
import com.tamayo.back.repositories.SpecialOfferRepository;

import lombok.extern.java.Log;

@Log
@Service
public class SalesOrderDetailServiceImp implements SalesOrderDetailService {
	
	private SalesorderdetailDao salesOrderDetailDao;
	private SalesOrderDetailRepository salesOrderDetailRep; 
	private ProductRepository productRep;
	private SpecialOfferRepository specialOfferRep;
	
	@Autowired
	public SalesOrderDetailServiceImp(SalesorderdetailDao salesOrderDetailDao, SalesOrderDetailRepository salesOrderDetailRep, ProductRepository productRep,SpecialOfferRepository specialOfferRep) {
		this.salesOrderDetailDao=salesOrderDetailDao;
		this.salesOrderDetailRep = salesOrderDetailRep;
		this.productRep = productRep;
		this.specialOfferRep = specialOfferRep;
	}
	
	public Salesorderdetail saveSaleOrderDetail(Salesorderdetail saleOrderDetail) {
		if(saleOrderDetail == null) return null;
		
		boolean result = saleOrderDetail.getUnitprice().compareTo(BigDecimal.ZERO) > 0 && saleOrderDetail.getUnitpricediscount().compareTo(BigDecimal.ZERO) >= 0 && productRep.existsById(saleOrderDetail.getSpecialofferproduct().getId().getProductid()) && specialOfferRep.existsById(saleOrderDetail.getSpecialofferproduct().getSpecialoffer().getSpecialofferid());
		
		if(!result) return null;
		
		saleOrderDetail = salesOrderDetailRep.save(saleOrderDetail);
		salesOrderDetailDao.Save(saleOrderDetail);
		return saleOrderDetail;
	}
	
	public Salesorderdetail editSaleOrderDetail(Salesorderdetail saleOrderDetail) {
		if(saleOrderDetail == null ||!salesOrderDetailRep.existsById(saleOrderDetail.getId().getSalesorderdetailid())) {	
			throw new RuntimeException();
		}
	
	Integer id = saleOrderDetail.getId().getSalesorderdetailid();
	Salesorderdetail sod = salesOrderDetailRep.findById(id).get();
	

	Timestamp ts = new Timestamp(System.currentTimeMillis());
	if(saleOrderDetail.getModifieddate() == ts  ) {
		sod.setModifieddate(saleOrderDetail.getModifieddate());
	}else {
			throw new RuntimeException();
		}
		
	sod.setSpecialofferproduct(saleOrderDetail.getSpecialofferproduct());
	salesOrderDetailDao.Save(saleOrderDetail);
		return sod;
		
	}
	
	


	
		
	
}
