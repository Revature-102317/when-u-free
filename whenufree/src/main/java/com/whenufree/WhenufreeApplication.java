package com.whenufree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.whenufree.services.UserService;
import com.whenufree.model.User;

import java.util.List;

@SpringBootApplication
public class WhenufreeApplication {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public static void main(String[] args) {
	SpringApplication.run(WhenufreeApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner runner() {
	return args -> {
	    // Logic for sweeping the database and transforming passwords into hashes
	    // List<User> allUsers = userService.findAll();
	    // System.out.println(allUsers);
	    // for(User u: allUsers){
	    // 	String pass = u.getPasswordHash();
	    // 	if(pass.length() < 30){
	    // 	    u.setPasswordHash(bCryptPasswordEncoder.encode(pass));
	    // 	    userService.save(u);
	    // 	}
	    // }
	};
    }
}
