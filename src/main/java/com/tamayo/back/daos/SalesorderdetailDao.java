package com.tamayo.back.daos;

import java.util.List;

import com.tamayo.back.model.Businessentity;
import com.tamayo.back.model.Product;
import com.tamayo.back.model.Salesorderdetail;
import com.tamayo.back.model.SalesorderdetailPK;


public interface SalesorderdetailDao {
	public void Save(Salesorderdetail entity);
	public void Delete(Salesorderdetail entity);
	public void Edit(Salesorderdetail entity);
	public Salesorderdetail findById(SalesorderdetailPK id);
	public Product findByProductid(Integer id);
	public Businessentity findByBusinessentityid(Integer id);
	public List<Salesorderdetail> findAll();
}
