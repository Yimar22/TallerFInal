package com.tamayo.back.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamayo.back.model.Unitmeasure;
import com.tamayo.back.repositories.UnitMeasureRepository;


@Service
public class UnitMeasureServiceImpl implements UnitMeasureService {

	private UnitMeasureRepository unitMeasureRepository;

	@Autowired
	public UnitMeasureServiceImpl(UnitMeasureRepository unitMeasureRepository) {
		super();
		this.unitMeasureRepository = unitMeasureRepository;
	}
	
	@Override
	public Unitmeasure saveUnitMeasure(Unitmeasure unitMeasure) {
		if(unitMeasure == null)
			return null;
		return unitMeasureRepository.save(unitMeasure);
	}
	
	@Override
	@Transactional
	public Unitmeasure upddateUnitMeasure(Unitmeasure unitMeasure) {
		if(unitMeasure == null)
			return null;
		Unitmeasure um = unitMeasureRepository.findById(unitMeasure.getUnitmeasurecode()).get();
		um.setName(unitMeasure.getName());
		um.setModifieddate(unitMeasure.getModifieddate());
		return unitMeasure;
	}
	
	@Override
	public boolean existsById(String unitMeasureCode) {
		return unitMeasureRepository.existsById(unitMeasureCode);
	}

	@Override
	public Iterable<Unitmeasure> findAll() {
		return unitMeasureRepository.findAll();
	}

	@Override
	public Optional<Unitmeasure> findById(String unitMeasureCode) {
		return unitMeasureRepository.findById(unitMeasureCode);
	}

	@Override
	public void delete(Unitmeasure unitmeasure) {
		unitMeasureRepository.delete(unitmeasure);
	}
	
}