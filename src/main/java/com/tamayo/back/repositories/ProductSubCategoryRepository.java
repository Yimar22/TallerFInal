package com.tamayo.back.repositories;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.tamayo.back.model.Productsubcategory;


@RepositoryRestResource
public interface ProductSubCategoryRepository extends CrudRepository<Productsubcategory, Integer>{

}
