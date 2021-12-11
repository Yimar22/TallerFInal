package com.tamayo.back.daos;

import java.sql.Timestamp;
import java.util.List;

import com.tamayo.back.model.Specialoffer;



public interface SpecialOfferDao {
	public void Save(Specialoffer entity);
	public void Delete(Specialoffer entity);
	public void Edit(Specialoffer entity);
	public Specialoffer findById(Integer id);
	public Specialoffer findByStartdate(Timestamp startdate);
	public Specialoffer findByEnddate(Timestamp enddate);
	public List<Specialoffer> findAll();
}
