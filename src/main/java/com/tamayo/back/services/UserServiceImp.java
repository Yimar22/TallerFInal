package com.tamayo.back.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamayo.back.model.User;
import com.tamayo.back.model.UserType;
import com.tamayo.back.repositories.UserRepository;


@Service
public class UserServiceImp implements UserService{

	private UserRepository userepo;
	
	@Autowired
	public UserServiceImp(UserRepository userepo) {
		this.userepo = userepo;
	}
	
	@Override
	public User save(User userr) {		
		return userepo.save(userr);
	}

	@Override
	public Optional<User> findById(long id) { 
		return userepo.findById(id);		
	}

	public Iterable<User> findAll() {
		return userepo.findAll();
	}


	public void delete(User user) {
		userepo.delete(user);

	}

	public UserType[] getTypes() {
		// TODO Auto-generated method stub
		return UserType.values();
	}

}
