package com.whenufree.controllers;

import com.whenufree.services.UserService;
import com.whenufree.services.FriendGroupService;
import com.whenufree.model.User;
import com.whenufree.model.FriendGroup;
import com.whenufree.jsonpojos.Named;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

@Controller
public class SocialNetworkController{
    private UserService userService;
    private FriendGroupService friendGroupService;

    @Autowired
    public SocialNetworkController(UserService userService,
				   FriendGroupService friendGroupService){
	this.userService = userService;
	this.friendGroupService = friendGroupService;
    }

    @RequestMapping(path = "/joingroup", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity joinGroup(@RequestBody Named group, Principal user){
	String username = user.getName();
	User currentUser = userService.findByEmail(username);
	FriendGroup friendGroup = friendGroupService.findById(group.getId());

	currentUser.joinGroup(friendGroup);
	userService.save(currentUser);
	
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/addfriend", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addFriend(@RequestBody Named friend, Principal user){
	String username = user.getName();
	User currentUser = userService.findByEmail(username);
	User friendUser = userService.findByUserId(friend.getId());

	currentUser.addFriend(friendUser);
	userService.save(currentUser);
	
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
