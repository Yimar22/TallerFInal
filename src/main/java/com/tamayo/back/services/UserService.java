package com.tamayo.back.services;

import java.util.Optional;

import com.tamayo.back.model.UserType;
import com.tamayo.back.model.Userr;


public interface UserService {
	public Userr save(Userr userr);
	public Optional<Userr> findById(long id);	
	public Iterable<Userr> findAll();
	public void delete(Userr user);
	public UserType[] getTypes();
}
