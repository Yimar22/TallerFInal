package com.tamayo.back.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tamayo.back.model.UserApp;
import com.tamayo.back.model.UserType;

@Repository
public interface UserRepository extends CrudRepository<UserApp, Long> {

	
	List<UserApp> findByUsername(String username);
	

}
