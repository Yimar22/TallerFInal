package com.tamayo.back.daos;


import java.util.List;

import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.model.SpecialofferproductPK;

public interface SpecialofferproductDao {
	public void Save(Specialofferproduct entity);
	public void Delete(Specialofferproduct entity);
	public void Edit(Specialofferproduct entity);
	public Specialofferproduct findById(SpecialofferproductPK id);
	public List<Specialofferproduct> findAll();
}
