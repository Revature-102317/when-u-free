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

	/*
	public static class UserPOJO {
		public Long userId;
		public String email;	
		public String firstname;
		public String lastname;
		public String phone;
		public String password;
		public Set<FriendsList> friendsList;
		public Set<Connection> connections;
		
		public UserPOJO() {}
	}
	*/

	@RequestMapping( path="/updateuser", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity updateUser( @RequestBody UserJson userJson,
			Principal pUser) {
		User dBUser = userService.findByEmail( pUser.getName());
		//System.out.println(userJson.toString());
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

	@RequestMapping( path="/deleteuser", method= RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity deleteUser( @RequestBody UserJson userJson,
			Principal pUser) {
		User dBUser = userService.findByEmail( pUser.getName());
		if( bCryptEnc.matches( userJson.getPassword(),
					dBUser.getPaswordHash())) {
			/*
			 * Two Options
			 * #1 Delete User
			 * userService.delete( dBUser);
			 *
			 * or just set User to invalid leaves room for recovery
			 * userService.setValid();
			 */
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

