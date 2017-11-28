package com.whenufree;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.whenufree.services.TimeSlotService;
import com.whenufree.services.UserService;
import com.whenufree.model.User;

@SpringBootApplication
public class WhenufreeApplication {

    @Autowired
    UserService userService;
    
    @Autowired
    TimeSlotService timeSlotService;
    
  //  public TimeSlotService getTimeSlotService(){
 //   	return timeSlotService;
  //  }

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public static void main(String[] args) {
	SpringApplication.run(WhenufreeApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner runner() {
	return args -> {
		/*
	    User u = new User();
	    u.setEmail("max.wang376@gmail.com");
	    u.setFirstname("Max");
	    u.setLastname("Wang");
	    u.setPhone("9173763581");
	    u.setPasswordHash(bCryptPasswordEncoder.encode("correct horse battery staple"));    
	    userService.save(u);
	    System.out.println("Insertion successful");*/
		//System.out.println("adding freetimes: " + userService.findByEmail("junjie2412@gmail.com")+" " + timeSlotService.findById((long) 1));
		userService.setTime(userService.findByEmail("junjie2412@gmail.com"), timeSlotService.findById((long) 2));
		userService.setTime(userService.findByEmail("junjie2412@gmail.com"), timeSlotService.findById((long) 3));
		userService.setTime(userService.findByEmail("junjie2412@gmail.com"), timeSlotService.findById((long) 4));
		userService.setTime(userService.findByEmail("junjie2412@gmail.com"), timeSlotService.findById((long) 5));
		userService.setTime(userService.findByEmail("junjie2412@gmail.com"), timeSlotService.findById((long) 6));
	};
    }
}
