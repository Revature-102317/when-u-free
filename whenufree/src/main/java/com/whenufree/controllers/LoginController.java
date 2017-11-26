package com.whenufree.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;


@Controller
public class LoginController{

    @RequestMapping(path="/user", method= RequestMethod.GET)
    @ResponseBody
    public Principal user(Principal user){
	return user;
    }

    @RequestMapping(path="/login", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity login(){
	return new ResponseEntity<>(HttpStatus.OK);
    }

}
