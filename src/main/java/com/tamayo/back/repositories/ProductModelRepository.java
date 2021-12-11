package com.tamayo.back.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tamayo.back.model.Productmodel;

@Repository
public interface ProductModelRepository extends CrudRepository<Productmodel, Integer> {
	
	boolean existsById(Integer id);

	Optional<Productmodel> findById(Integer id);

}
