package com.tamayo.back.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tamayo.back.model.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer>{

}
