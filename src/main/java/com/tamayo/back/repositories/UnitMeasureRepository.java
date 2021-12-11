package com.tamayo.back.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tamayo.back.model.Unitmeasure;


@Repository
public interface UnitMeasureRepository extends CrudRepository<Unitmeasure, Integer>{

	public Optional<Unitmeasure> findById(String unitMeasureCode);

	public boolean existsById(String unitMeasureCode);

}
