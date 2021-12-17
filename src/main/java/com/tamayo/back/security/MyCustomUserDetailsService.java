package com.tamayo.back.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tamayo.back.model.Userr;
import com.tamayo.back.repositories.UserRepository;

import lombok.extern.java.Log;

@Log
@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	@Autowired
	public MyCustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Userr users = userRepository.findByUsername(username);
		
		if (users != null) {
			User.UserBuilder builder = User.withUsername(username).password(users.getUserPassword()).roles(users.getUsertype().toString());
			return builder.build();
		} else {
			log.info("not found");
			throw new UsernameNotFoundException("User not found.");
		}
	}
}