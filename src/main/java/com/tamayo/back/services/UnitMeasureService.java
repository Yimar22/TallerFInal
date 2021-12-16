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
	public Unitmeasure saveUnitMeasure2(Unitmeasure unitMeasure);
	public Unitmeasure upddateUnitMeasure2(Unitmeasure unitMeasure);
	public boolean existsById2(String unitMeasureCode);
	public Iterable<Unitmeasure> findAll2();
	public Optional<Unitmeasure> findById2(String unitMeasureCode);
	public void delete2(Unitmeasure unitmeasure);
}
