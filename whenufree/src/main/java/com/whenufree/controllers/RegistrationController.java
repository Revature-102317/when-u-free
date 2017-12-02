package com.whenufree.controllers;

import com.whenufree.services.UserService;
import com.whenufree.model.User;
import com.whenufree.jsonpojos.UserJson;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class RegistrationController{
    
    private BCryptPasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public RegistrationController(BCryptPasswordEncoder passwordEncoder,
				  UserService userService){
	this.passwordEncoder = passwordEncoder;
	this.userService = userService;
    }


    @RequestMapping(path = "/register", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity joinGroup(@RequestBody UserJson newUser){
	User u = new User();
	u.setEmail(newUser.getEmail());
	u.setFirstname(newUser.getFirstname());
	u.setLastname(newUser.getLastname());
	u.setPhone(newUser.getPhone());
	u.setPasswordHash(passwordEncoder.encode(newUser.getPassword()));
	userService.save(u);

	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
}
