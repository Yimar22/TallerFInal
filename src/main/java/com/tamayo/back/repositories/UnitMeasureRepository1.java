package com.tamayo.back.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tamayo.back.model.Unitmeasure;


@RepositoryRestResource
public interface UnitMeasureRepository1 extends CrudRepository<Unitmeasure, String>{

	

}
