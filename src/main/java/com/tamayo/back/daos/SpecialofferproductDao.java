package com.tamayo.back.daos;


import java.util.List;

import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.model.SpecialofferproductPK;

public interface SpecialofferproductDao {
	public void save(Specialofferproduct entity);
	public void delete(Specialofferproduct entity);
	public void edit(Specialofferproduct entity);
	public Specialofferproduct findById(SpecialofferproductPK id);
	public List<Specialofferproduct> findAll();
}
