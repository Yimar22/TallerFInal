package com.tamayo.back.services;

import java.util.Optional;

import com.tamayo.back.model.Specialoffer;

public interface SpecialOfferService {

	public Specialoffer saveoffer(Specialoffer specialoffer);
	public Specialoffer editoffer(Specialoffer specialoffer);
	boolean existsById(Integer specialOfferId);

	Iterable<Specialoffer> findAll();

	Specialoffer findById(Integer id);

	void delete(Specialoffer specialoffer);

}
