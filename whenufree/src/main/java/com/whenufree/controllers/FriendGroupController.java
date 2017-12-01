package com.whenufree.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whenufree.model.FriendGroup;
import com.whenufree.model.TimeSlot;
import com.whenufree.model.User;
import com.whenufree.services.FriendGroupService;
import com.whenufree.services.TimeSlotService;
import com.whenufree.services.UserService;

@Controller
public class FriendGroupController {
	
	private UserService userService;
	
	private TimeSlotService timeSlotService;
	
	private FriendGroupService friendGroupService;
	
	@Autowired
	public FriendGroupController(UserService userService, TimeSlotService timeSlotService, FriendGroupService friendGroupService){
		this.userService = userService;
		this.timeSlotService = timeSlotService;
		this.friendGroupService = friendGroupService;
	}
	
	//path that returns friend groups of the current user
	@RequestMapping(path="/myfriendgroups", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<FriendGroup>> getAll(Principal user){
		User u = userService.findByEmail(user.getName());
		List<FriendGroup> fgList = friendGroupService.findByUser(u);
		for (int a = 0; a < fgList.size(); a++){
			fgList.get(a).getConnections().removeAll(fgList.get(a).getConnections());
		}
		return new ResponseEntity<List<FriendGroup>>(fgList, HttpStatus.OK);
	}

}
