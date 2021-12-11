package com.tamayo.back.services;

import java.util.Optional;

import com.tamayo.back.model.User;
import com.tamayo.back.model.UserType;


public interface UserService {
	public User save(User userr);
	public Optional<User> findById(long id);	
	public Iterable<User> findAll();
	public void delete(User user);
	public UserType[] getTypes();
}
