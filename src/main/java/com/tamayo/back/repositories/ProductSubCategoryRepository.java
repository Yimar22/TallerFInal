package com.tamayo.back.repositories;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tamayo.back.model.Productsubcategory;


@Repository
public interface ProductSubCategoryRepository extends CrudRepository<Productsubcategory, Long>{

	boolean existsById(Integer id);

	Optional<Productsubcategory> findById(Integer id);

}
