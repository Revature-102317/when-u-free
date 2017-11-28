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
public class LoginController{

    private UserService userService;

    @Autowired
    public LoginController(UserService userService){
	this.userService = userService;
    }
    
    @RequestMapping(path="/user", method= RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserJson> user(Principal user){
	String username = user.getName();
	User u = userService.findByEmail(username);
	UserJson ujson = new UserJson(u);
	return new ResponseEntity<>(ujson, HttpStatus.OK);
    }

    @RequestMapping(path="/login", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity login(){
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
