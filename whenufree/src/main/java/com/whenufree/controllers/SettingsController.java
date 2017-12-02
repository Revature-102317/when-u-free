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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.Principal;


@Controller
public class SettingsController {
	private UserService userService;
	private BCryptPasswordEncoder bCryptEnc;

	@Autowired
	public SettingsController( UserService userService,
			BCryptPasswordEncoder bCryptEnc) {
		this.userService = userService;
		this.bCryptEnc = bCryptEnc;
	}

	@RequestMapping( path="/user", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity updateUser( @RequestBody UserJson userJson,
			Principal pUser) {
		User dBUser = userService.findByEmail( pUser.getName());
		if( bCryptEnc.matches( userJson.getPassword(),
					dBUser.getPasswordHash())) {
			dBUser.setEmail( userJson.getEmail());
			dBUser.setFirstname( userJson.getFirstname());
			dBUser.setLastname( userJson.getLastname());
			dBUser.setPhone( userJson.getPhone());
			dBUser.setPasswordHash( userJson.getPassword());
		}
		userService.save( dBUser);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
