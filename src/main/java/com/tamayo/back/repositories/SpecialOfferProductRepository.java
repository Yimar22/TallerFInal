package com.tamayo.back.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.model.SpecialofferproductPK;
@RepositoryRestResource
public interface SpecialOfferProductRepository extends CrudRepository<Specialofferproduct, SpecialofferproductPK>{

	boolean existsById(Integer productid);

	Optional<Specialofferproduct> findById(Integer id);

}
