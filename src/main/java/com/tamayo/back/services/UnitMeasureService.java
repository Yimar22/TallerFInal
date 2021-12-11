package com.tamayo.back.services;

import java.util.Optional;

import com.tamayo.back.model.Unitmeasure;


public interface UnitMeasureService {

	public Unitmeasure saveUnitMeasure(Unitmeasure unitMeasure);
	public Unitmeasure upddateUnitMeasure(Unitmeasure unitMeasure);
	public boolean existsById(String unitMeasureCode);
	public Iterable<Unitmeasure> findAll();
	public Optional<Unitmeasure> findById(String unitMeasureCode);
	public void delete(Unitmeasure unitmeasure);
}
