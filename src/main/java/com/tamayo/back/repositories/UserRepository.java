package com.tamayo.back.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tamayo.back.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
	List<User> findByUsername(String username);
}
