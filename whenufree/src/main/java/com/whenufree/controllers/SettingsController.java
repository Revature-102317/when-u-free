package com.whenufree.controllers;

import com.whenufree.jsonpojos.UserJson;
import com.whenufree.model.User;
import com.whenufree.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

@Controller
public class SettingsController {
	private UserService userService;

	@Autowired
	public SettingsController( UserService userService) {
		this.userService = userService;
	}

	/*
	@RequestMapping( path="/user", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity updateUser(@RequestBody ) {
		return ResponseEntity<>(HttpStatus.NO_CONTENT);
	}*/
	
}
		
