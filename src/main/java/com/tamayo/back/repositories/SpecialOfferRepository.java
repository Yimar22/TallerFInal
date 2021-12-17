package com.tamayo.back.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.tamayo.back.model.Specialoffer;
@RepositoryRestResource
public interface SpecialOfferRepository extends CrudRepository<Specialoffer, Integer>{

}
