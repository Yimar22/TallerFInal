package com.tamayo.back.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamayo.back.daos.SpecialofferproductDao;
import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.model.SpecialofferproductPK;
import com.tamayo.back.repositories.SpecialOfferProductRepository;

import lombok.extern.java.Log;
@Log
@Service
public class SpecialOfferProductServiceImp implements SpecialOfferProductService {

	private SpecialofferproductDao specialOfferProductDao;
	private SpecialOfferProductRepository sopr;
	
	@Autowired
	public SpecialOfferProductServiceImp(SpecialofferproductDao specialOfferProductDao, SpecialOfferProductRepository sopr) {
		this.specialOfferProductDao=specialOfferProductDao;
		this.sopr=sopr;
	}
	
	@Override
	public Specialofferproduct save(Specialofferproduct specialofferproduct) {
		//
		if(specialofferproduct == null || specialofferproduct.getId() == null || specialofferproduct.getId().getProductid() == null
				|| specialofferproduct.getSpecialoffer() == null || specialofferproduct.getSpecialoffer().getSpecialofferid() == null) {
			return null;
		}
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		if(specialofferproduct.getModifieddate() == ts  ) {
			return null;
		}
		
		specialofferproduct = sopr.save(specialofferproduct);
		return specialofferproduct;		
	}

	@Override
	public Specialofferproduct edit(Specialofferproduct specialofferproduct) {
		if(specialofferproduct == null ||!sopr.existsById(specialofferproduct.getId().getProductid()) ||!sopr.existsById(specialofferproduct.getSpecialoffer().getSpecialofferid())) {	
			throw new RuntimeException();
		}
	
	Integer id = specialofferproduct.getSpecialoffer().getSpecialofferid();
	Specialofferproduct sop = sopr.findById(id).get();
	

	Timestamp ts = new Timestamp(System.currentTimeMillis());
	if(specialofferproduct.getModifieddate() == ts  ) {
		sop.setModifieddate(specialofferproduct.getModifieddate());
	}else {
			throw new RuntimeException();
		}
		
	specialofferproduct.setSpecialoffer(specialofferproduct.getSpecialoffer());
		return sop;
		
	}
	
	@Override
	public boolean existsById(SpecialofferproductPK specialOfferProductId) {
		return sopr.existsById(specialOfferProductId);
	}
	
	@Override
	public Iterable<Specialofferproduct> findAll() {
		return sopr.findAll();
	}
	
	@Override
	public Optional<Specialofferproduct> findById(SpecialofferproductPK id) {
		return sopr.findById(id);
	}
	
	@Override
	public void delete(Specialofferproduct specioffprod) {
		sopr.delete(specioffprod);
	}

	
	
}
