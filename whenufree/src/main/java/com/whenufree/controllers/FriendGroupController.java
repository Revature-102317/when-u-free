package com.whenufree.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whenufree.jsonpojos.UserJson;
import com.whenufree.model.Connection;
import com.whenufree.model.FriendGroup;
import com.whenufree.model.GroupFreeTime;
import com.whenufree.model.TimeSlot;
import com.whenufree.model.User;
import com.whenufree.services.FriendGroupService;
import com.whenufree.services.TimeSlotService;
import com.whenufree.services.UserService;
import javax.inject.Provider;

@Controller
public class FriendGroupController {
	
	private UserService userService;
	
	private TimeSlotService timeSlotService;
	
	private FriendGroupService friendGroupService;
	
	@Autowired
	private Provider<FriendGroup> activeFriendGroup;
	
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
	
	//path that returns the friendgroup that was clicked on
		@RequestMapping(path="/friendgroup", method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<FriendGroup> getFriendGroup(){
			/*
			FriendGroup fg = friendGroupService.findByName(activeFriendGroup.get().getName());
			fg.getConnections().removeAll(fg.getConnections());*/
			FriendGroup fg = friendGroupService.findByName(activeFriendGroup.get().getName()); 
			fg.getConnections().removeAll(fg.getConnections());
			return new ResponseEntity<FriendGroup>(fg, HttpStatus.OK);
		}
		
	//path that returns the list of users in the friend group
		@RequestMapping(path="/friendgroupusers", method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<List<UserJson>> getFriendGroupUsers(){
			FriendGroup fg = friendGroupService.findByName(activeFriendGroup.get().getName());
			List<UserJson> users = new ArrayList<UserJson>();
			//connections of that friend group
			Set<Connection> connections = fg.getConnections();
			//list of approved connections
			Set<Connection> approvedConnections= new HashSet<Connection>();
			
			//This loop creates a list of approved connections
			Iterator<Connection> i = connections.iterator();
			int counter = 0;
			while(i.hasNext()){
				Connection c = i.next();
				if(c.getFriendGroupStatus().getStatusId() == 1){
					approvedConnections.add(c);
				}
			}
			
			//This generates a list of users that are approved in that list
			Iterator<Connection> it = approvedConnections.iterator();
			int count = 0;
			while(it.hasNext()){
				UserJson ujson = new UserJson(it.next().getUser());
		        users.add(ujson);
		        users.get(count).getConnections().removeAll(users.get(count).getConnections());
		        users.get(count).getFriendsList().removeAll(users.get(count).getFriendsList());
		        count++;
		     }
			return new ResponseEntity<List<UserJson>>(users, HttpStatus.OK);
		}
	
	//path the post request of gotten friend group was sent to
	@RequestMapping(path="/clickedfriendgroup", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> receiveClickedOnFriendGroup(@RequestBody String receivedFriendGroup){
		//this is returned {"friendgroup":"name"}
		//We will now substring this
		FriendGroup fg = activeFriendGroup.get();
		String friendgroupSubStringed = receivedFriendGroup.substring(16, receivedFriendGroup.length()-2);
		FriendGroup fg2 = friendGroupService.findByName(friendgroupSubStringed);
		fg.setConnections(fg2.getConnections());
		fg.setFriendGroupId(fg2.getFriendGroupId());
		fg.setName(fg2.getName());
		System.out.println(activeFriendGroup.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//path that returns the group free times of the current friend group
	@RequestMapping(path="/groupfreetimes", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<GroupFreeTime>> getGroupFreeTimes(){
		FriendGroup fg = friendGroupService.findByFriendGroupName(activeFriendGroup.get().getName());
		List<GroupFreeTime> gft = friendGroupService.getGroupFreeTimes(fg);
		for(int a = 0; a < gft.size(); a++){
			gft.get(a).getFriendGroup().getConnections().removeAll(gft.get(a).getFriendGroup().getConnections());
		}
		return new ResponseEntity<List<GroupFreeTime>>(gft, HttpStatus.OK);
	}
}
