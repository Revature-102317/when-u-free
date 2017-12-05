package com.whenufree.controllers;

import com.whenufree.services.UserService;
import com.whenufree.services.FriendGroupService;
import com.whenufree.model.User;
import com.whenufree.model.FriendGroup;
import com.whenufree.model.Connection;
import com.whenufree.model.FreeTime;
import com.whenufree.model.TimeSlot;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

@Controller
public class SchedulerController{
    
    private UserService userService;
    private FriendGroupService friendGroupService;

    @Autowired
    public SchedulerController(UserService userService,
				   FriendGroupService friendGroupService){
	this.userService = userService;
	this.friendGroupService = friendGroupService;
    }

    public static class SchedulerJson{
	public Long groupId;
	public Long day;
	public Long time;
	public Long duration;
    }
    @RequestMapping(path = "/scheduleevent", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public ResponseEntity joinGroup(@RequestBody SchedulerJson sj){
	FriendGroup group = friendGroupService.findById(sj.groupId);
	Long start = sj.day * 24 + sj.time + 1;
	Long end = start + sj.duration;
	for(Connection c : group.getConnections()){
	    User u = c.getUser();
	    for(FreeTime t: u.getFreeTimes()) {
	        TimeSlot ts = t.getTimeSlot();
		if(start <= ts.getTimeSlotId() && ts.getTimeSlotId() < end){  
		    t.setScheduled(true);
		}
	    }
	}
	friendGroupService.save(group);
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
