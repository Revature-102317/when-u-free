package com.whenufree.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.whenufree.services.TimeSlotService;
import com.whenufree.services.UserService;

@Controller
public class FriendGroupController {
	
	private UserService userService;
	
	private TimeSlotService timeSlotService;
	
	@Autowired
	public FriendGroupController(UserService userService, TimeSlotService timeSlotService){
		this.userService = userService;
		this.timeSlotService = timeSlotService;
	}

}
