package com.tamayo.back.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tamayo.back.model.Workorder;

@RepositoryRestResource
public interface WorkorderRepository extends CrudRepository<Workorder, Integer>{

}
