package com.tamayo.back.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamayo.back.daos.SpecialOfferDao;
import com.tamayo.back.model.Product;
import com.tamayo.back.model.Productcategory;
import com.tamayo.back.model.Specialoffer;
import com.tamayo.back.repositories.SpecialOfferRepository;

import lombok.extern.java.Log;
@Log
@Service
public class SpecialOfferServiceImp implements SpecialOfferService{

	private SpecialOfferDao specialofferDao;
	private SpecialOfferRepository sor;
	
	@Autowired
	public SpecialOfferServiceImp(SpecialOfferDao specialofferDao, SpecialOfferRepository sor) {
		this.specialofferDao=specialofferDao;
		this.sor=sor;
	}

	@Override
	public Specialoffer saveoffer(Specialoffer specialoffer) {
		if(specialoffer == null) {
			return null;
		}
	
		boolean result = specialoffer.getCategory() != null && specialoffer.getDiscountpct().compareTo(BigDecimal.ZERO) > 0 && specialoffer.getModifieddate().equals(new Timestamp(System.currentTimeMillis()));
		if(!result) return null;
		specialoffer = sor.save(specialoffer);
		specialofferDao.save(specialoffer);
		return specialoffer;
	}

	@Override
	public Specialoffer editoffer(Specialoffer specialoffer) {
		if(specialoffer == null ||!sor.existsById(specialoffer.getSpecialofferid())) {	
			throw new RuntimeException();
		}
	
	Integer id = specialoffer.getSpecialofferid();
	Specialoffer currentoffer = sor.findById(id).get();
	
	if(specialoffer.getCategory().equals("") ){
		return null;
		}
	
	if(specialoffer.getDiscountpct().compareTo(specialoffer.getDiscountpct())<0 ) {
		currentoffer.setDiscountpct(specialoffer.getDiscountpct());
		}else {
			throw new RuntimeException();
		}
	Timestamp ts = new Timestamp(System.currentTimeMillis());
		if(specialoffer.getModifieddate().after(ts)|| specialoffer.getModifieddate().before(ts)) {
			currentoffer.setModifieddate(ts);
		}else {
			throw new RuntimeException();
		}
		currentoffer.setCategory(specialoffer.getCategory()); 
		specialofferDao.edit(specialoffer);
		return currentoffer;
	}
	
	@Override
	public boolean existsById(Integer specialOfferId) {
		return sor.existsById(specialOfferId);
	}
	
	@Override
	public Iterable<Specialoffer> findAll() {
		return specialofferDao.findAll();
	}
	
	@Override
	public Specialoffer findById(Integer id) {
		return specialofferDao.findById(id);
	}
	
	@Override
	public void delete(Specialoffer specioff) {
		specialofferDao.delete(specioff);
	}
	
	
}
