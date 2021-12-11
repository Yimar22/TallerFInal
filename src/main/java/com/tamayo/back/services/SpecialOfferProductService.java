package com.tamayo.back.services;

import java.util.Optional;

import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.model.SpecialofferproductPK;

public interface SpecialOfferProductService {
	
	public Specialofferproduct save(Specialofferproduct specialofferproduct);
	public Specialofferproduct edit(Specialofferproduct specialofferproduct);
	
	
}
