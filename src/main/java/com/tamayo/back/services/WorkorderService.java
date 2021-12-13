package com.tamayo.back.services;

import java.util.Optional;

import com.tamayo.back.model.Workorder;


public interface WorkorderService {
	

	Workorder saveWorkorder(Workorder workorder);
	
	Iterable<Workorder> saveAll(Iterable<Workorder> workorders);
	
	Optional<Workorder> findById(Integer id);
	
	boolean existsById(Integer id);
	
	Iterable<Workorder> findAll();
	
	Iterable<Workorder> findAllById(Iterable<Integer> ids);
	
	long count();
	
	void deleteById(Integer id);
	
	void delete(Workorder workorder);
	
	void deleteAll(Iterable<Workorder> workorders);

	void deleteAll();
	
	void editWorkorder(Workorder workorder);
		
}
