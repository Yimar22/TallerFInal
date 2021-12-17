package com.tamayo.back.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tamayo.back.model.Location;

@RepositoryRestResource
public interface LocationRepository extends CrudRepository<Location, Integer>{

}
