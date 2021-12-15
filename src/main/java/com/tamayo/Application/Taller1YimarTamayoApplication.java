package com.tamayo.Application;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.tamayo.back.model.User;
import com.tamayo.back.model.UserType;
import com.tamayo.back.services.UserService;
import com.tamayo.back.services.UserServiceImp;


@SpringBootApplication
@ComponentScan(basePackages = "com.tamayo")
public class Taller1YimarTamayoApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Taller1YimarTamayoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner dummy(UserService userService) {
		return (args) -> {
				User u = new User(1,null, "YimarT","0000", UserType.administrador, null);
				userService.save(u);
			
		};

	}

}
