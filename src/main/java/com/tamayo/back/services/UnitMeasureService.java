package com.tamayo.back.services;

import java.util.Optional;

import com.tamayo.back.model.Unitmeasure;


public interface UnitMeasureService {

	public Unitmeasure saveUnitMeasure1(Unitmeasure unitMeasure);
	public Unitmeasure upddateUnitMeasure1(Unitmeasure unitMeasure);
	public boolean existsById1(String unitMeasureCode);
	public Iterable<Unitmeasure> findAll1();
	public Optional<Unitmeasure> findById1(String unitMeasureCode);
	public void delete1(Unitmeasure unitmeasure);
}
