package com.tamayo.back.services;

import java.util.Optional;

import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.model.SpecialofferproductPK;

public interface SpecialOfferProductService {
	
	public Specialofferproduct save(Specialofferproduct specialofferproduct);
	public Specialofferproduct edit(Specialofferproduct specialofferproduct);

	boolean existsById(SpecialofferproductPK specialOfferProductId);

	Iterable<Specialofferproduct> findAll();

	Optional<Specialofferproduct> findById(SpecialofferproductPK id);

	void delete(Specialofferproduct specioffprod);
	
}
