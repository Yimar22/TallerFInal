package com.tamayo.back.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserApp {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	private String username;
	private String userPassword;
	private UserType userType;
}
