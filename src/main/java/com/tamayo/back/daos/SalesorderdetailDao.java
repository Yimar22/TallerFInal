package com.tamayo.back.daos;

import java.util.List;

import com.tamayo.back.model.Businessentity;
import com.tamayo.back.model.Product;
import com.tamayo.back.model.Salesorderdetail;
import com.tamayo.back.model.SalesorderdetailPK;


public interface SalesorderdetailDao {
	public void save(Salesorderdetail entity);
	public void delete(Salesorderdetail entity);
	public void edit(Salesorderdetail entity);
	public Salesorderdetail findById(Integer id);
	public Product findByProductid(Integer id);
	public Businessentity findByBusinessentityid(Integer id);
	public List<Salesorderdetail> findAll();
}
