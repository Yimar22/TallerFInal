package com.tamayo.back.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tamayo.back.model.Unitmeasure;
import com.tamayo.back.repositories.UnitMeasureRepository1;


@Service
public class UnitMeasureServiceImpl {

	private UnitMeasureRepository1 unitMeasureRepository1;
	

	public UnitMeasureServiceImpl(UnitMeasureRepository1 unitMeasureRepository1) {
		this.unitMeasureRepository1 = unitMeasureRepository1;
	}
	
	public Unitmeasure saveUnitMeasure1(Unitmeasure unitMeasure) {
		return unitMeasureRepository1.save(unitMeasure);
	}
	
	public Iterable<Unitmeasure> saveAll(Iterable<Unitmeasure> locs){
		for(Unitmeasure loc:locs) {
			saveUnitMeasure1(loc);
		}
		return locs;
	}
	
	public Optional<Unitmeasure> findById1(String unitMeasureCode) {
		return unitMeasureRepository1.findById(unitMeasureCode);
	}
	
	public boolean existsById1(String unitMeasureCode) {
		return unitMeasureRepository1.existsById(unitMeasureCode);
	}
	
	public Iterable<Unitmeasure> findAll1() {
		return unitMeasureRepository1.findAll();
	}
	
	public long count() {
		return unitMeasureRepository1.count();
	}
	
	public void deleteById(String code) {
		unitMeasureRepository1.deleteById(code);
	}
	
	public void delete1(Unitmeasure unitmeasure) {
		deleteById(unitmeasure.getUnitmeasurecode());
	}
	
	public void deleteAll(Iterable<Unitmeasure> locs) {
		unitMeasureRepository1.deleteAll(locs);
	}
	
	public void deleteAll() {
		unitMeasureRepository1.deleteAll();
	}
	
	public void editUnitMeasure1(String id, String name) {
		Unitmeasure um = findById1(id).get();
		um.setName(name);
		um.setModifieddate(new Timestamp(System.currentTimeMillis()));
		unitMeasureRepository1.save(um);
	}
	
	
}