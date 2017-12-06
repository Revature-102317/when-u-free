package com.whenufree.controllers;

import com.whenufree.services.UserService;
import com.whenufree.services.EmailService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

@Controller
public class SocialNetworkController{
    private UserService userService;
    private FriendGroupService friendGroupService;
    
    //To send an email, just do the following:
    //emailService.send("example@email.com", "Title of the Email", "Message Body of Email");
    private EmailService emailService = new EmailService();

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
    
    @RequestMapping(path = "/acceptgroup", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity acceptGroup(@RequestBody Named group, Principal user){
	String username = user.getName();
	User currentUser = userService.findByEmail(username);
	FriendGroup g = friendGroupService.findById(group.getId());

	currentUser.acceptGroup(g);
	userService.save(currentUser);
	
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/leavegroup", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity leaveGroup(@RequestBody Named group, Principal user){
    	
	String username = user.getName();
	User currentUser = userService.findByEmail(username);
	FriendGroup g = friendGroupService.findById(group.getId());

	currentUser.leaveGroup(g);
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

    @RequestMapping(path = "/acceptfriend", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity acceptFriend(@RequestBody Named friend, Principal user){
	String username = user.getName();
	User currentUser = userService.findByEmail(username);
	User friendUser = userService.findByUserId(friend.getId());

	currentUser.acceptFriend(friendUser);
	userService.save(currentUser);
	
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/removefriend", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity removeFriend(@RequestBody Named friend, Principal user){
	String username = user.getName();
	User currentUser = userService.findByEmail(username);
	User friendUser = userService.findByUserId(friend.getId());

	currentUser.removeFriend(friendUser);
	userService.save(currentUser);
	
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    
    @RequestMapping(path = "/getuser/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Named> getUser(@PathVariable("id") Long id){
	User u = userService.findByUserId(id);
	Named n = new Named(u);
	return new ResponseEntity<>(n, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/getfriendgroup/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Named> getFriendGroup(@PathVariable("id") Long id){
	FriendGroup fg = friendGroupService.findById(id);
	Named n = new Named(fg);
	return new ResponseEntity<>(n, HttpStatus.OK);	
    }

}
