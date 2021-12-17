package com.tamayo.back.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamayo.back.model.UserType;
import com.tamayo.back.model.Userr;
import com.tamayo.back.repositories.UserRepository;


@Service
public class UserServiceImp implements UserService{

	private UserRepository userepo;
	
	@Autowired
	public UserServiceImp(UserRepository userepo) {
		this.userepo = userepo;
	}
	
	@Override
	public Userr save(Userr userr) {		
		return userepo.save(userr);
	}

	@Override
	public Optional<Userr> findById(long id) { 
		return userepo.findById(id);		
	}

	public Iterable<Userr> findAll() {
		return userepo.findAll();
	}


	public void delete(Userr user) {
		userepo.delete(user);

	}

	public UserType[] getTypes() {
		// TODO Auto-generated method stub
		return UserType.values();
	}

}
