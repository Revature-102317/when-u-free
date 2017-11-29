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

import java.util.List;

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

	};
    }
}
