package com.tamayo.back.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tamayo.back.model.UserApp;
import com.tamayo.back.model.UserType;

@Service
public interface UserService {
	public UserApp save(UserApp user);

	public Optional<UserApp> findById(long id);

	public Iterable<UserApp> findAll();

	public void delete(UserApp user);

	public UserType[] getTypes();
}
