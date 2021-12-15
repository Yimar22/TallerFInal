package com.tamayo.back.daos;

import java.sql.Timestamp;
import java.util.List;

import com.tamayo.back.model.Specialoffer;



public interface SpecialOfferDao {
	public void save(Specialoffer entity);
	public void delete(Specialoffer entity);
	public void edit(Specialoffer entity);
	public Specialoffer findById(Integer id);
	public Specialoffer findByStartdate(Timestamp startdate);
	public Specialoffer findByEnddate(Timestamp enddate);
	public List<Specialoffer> findAll();
}
