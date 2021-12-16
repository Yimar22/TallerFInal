package com.tamayo.back.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamayo.back.model.Unitmeasure;
import com.tamayo.back.repositories.UnitMeasureRepository1;
import com.tamayo.back.repositories.UnitMeasureRepository2;


@Service
public class UnitMeasureServiceImpl implements UnitMeasureService {

	private UnitMeasureRepository1 unitMeasureRepository1;
	
	private UnitMeasureRepository2 unitMeasureRepository2;

	@Autowired
	public UnitMeasureServiceImpl(UnitMeasureRepository1 unitMeasureRepository1, UnitMeasureRepository2 unitMeasureRepository2) {
		super();
		this.unitMeasureRepository1 = unitMeasureRepository1;
		this.unitMeasureRepository2 = unitMeasureRepository2;
	}
	
	@Override
	public Unitmeasure saveUnitMeasure1(Unitmeasure unitMeasure) {
		if(unitMeasure == null)
			return null;
		return unitMeasureRepository1.save(unitMeasure);
	}
	
	@Override
	public Unitmeasure saveUnitMeasure2(Unitmeasure unitMeasure) {
		if(unitMeasure == null)
			return null;
		return unitMeasureRepository2.save(unitMeasure);
	}
	
	@Override
	@Transactional
	public Unitmeasure upddateUnitMeasure1(Unitmeasure unitMeasure) {
		if(unitMeasure == null)
			return null;
		Unitmeasure um = unitMeasureRepository1.findById(unitMeasure.getUnitmeasurecode()).get();
		um.setName(unitMeasure.getName());
		um.setModifieddate(unitMeasure.getModifieddate());
		return unitMeasure;
	}
	
	@Override
	@Transactional
	public Unitmeasure upddateUnitMeasure2(Unitmeasure unitMeasure) {
		if(unitMeasure == null)
			return null;
		Unitmeasure um = unitMeasureRepository2.findById(unitMeasure.getUnitmeasurecode()).get();
		um.setName(unitMeasure.getName());
		um.setModifieddate(unitMeasure.getModifieddate());
		return unitMeasure;
	}
	
	@Override
	public boolean existsById1(String unitMeasureCode) {
		return unitMeasureRepository1.existsById(unitMeasureCode);
	}
	
	@Override
	public boolean existsById2(String unitMeasureCode) {
		return unitMeasureRepository2.existsById(unitMeasureCode);
	}
	
	@Override
	public Iterable<Unitmeasure> findAll1() {
		return unitMeasureRepository1.findAll();
	}
	
	@Override
	public Iterable<Unitmeasure> findAll2() {
		return unitMeasureRepository2.findAll();
	}
	
	@Override
	public Optional<Unitmeasure> findById1(String unitMeasureCode) {
		return unitMeasureRepository1.findById(unitMeasureCode);
	}

	@Override
	public Optional<Unitmeasure> findById2(String unitMeasureCode) {
		return unitMeasureRepository2.findById(unitMeasureCode);
	}
	
	@Override
	public void delete1(Unitmeasure unitmeasure) {
		unitMeasureRepository1.delete(unitmeasure);
	}
	
	@Override
	public void delete2(Unitmeasure unitmeasure) {
		unitMeasureRepository2.delete(unitmeasure);
	}
	
}