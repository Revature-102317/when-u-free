package com.whenufree.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whenufree.model.TimeSlot;
import com.whenufree.model.User;
import com.whenufree.services.TimeSlotService;
import com.whenufree.services.UserService;

@Controller
public class SetTimesController {
	
	private UserService userService;
	
	private TimeSlotService timeSlotService;
	
	private List<String> defaultTimes = new ArrayList<String>();
	
	private int count = 0;
	
	//Constructor
	@Autowired
	public SetTimesController(UserService userService, TimeSlotService timeSlotService){
		this.userService = userService;
		this.timeSlotService = timeSlotService;
	}
	
	//The path that returns all the timeslots
	@RequestMapping(path="/timeslots", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<TimeSlot>> getAll(){
		
		List<TimeSlot> t = timeSlotService.findAll();
		
		return new ResponseEntity<List<TimeSlot>>(t, HttpStatus.OK);
	}
	
	//The path that returns a timeslot by its id
	@RequestMapping(path="/timeslot/{id}", method={RequestMethod.GET, RequestMethod.POST},
			consumes="*/*", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<TimeSlot> getOne(@PathVariable("id") Long x){
		
		TimeSlot t = timeSlotService.findById(x);
		
		return new ResponseEntity<TimeSlot>(t, HttpStatus.OK);
	}
	
	//The path that returns the default times set by the user by the Angular
	@RequestMapping(path="/defaulttime", method={RequestMethod.GET, RequestMethod.POST},
			consumes="*/*", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<String>> getWeekTime(){
		return new ResponseEntity<List<String>>(defaultTimes, HttpStatus.OK);
	}
	
	//This path returns the default times of the current user
	//The path that returns a timeslot by its id
		@RequestMapping(path="/mydefaulttimes", method={RequestMethod.GET, RequestMethod.POST},
				consumes="*/*", produces=MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<TimeSlot>> getDefaultTimesOfCurrentUser(Principal user){
			User u = userService.findByEmail(user.getName());
			return new ResponseEntity<List<TimeSlot>>(userService.getFreeTimesByUser(u), HttpStatus.OK);
		}
	
	//The path that sets the default time
	@RequestMapping(path="/setdefaulttime", method= RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> setdefaulttime(@RequestBody String weektime, Principal user){
	User u = userService.findByEmail(user.getName());
	//This removes the post request from the list(not database) if the list already has it
	if (defaultTimes.contains(weektime.substring(7, 23))){
		defaultTimes.remove(weektime.substring(7, 23));
	}
	else {
		//This method submits the list to the database
		if (weektime.substring(2, 8).equals("submit")){
			userService.setDefaultTime(u, defaultTimes);
			defaultTimes.removeAll(defaultTimes);
		}
		//This method adds the post request to the list(not database)
		else{
		defaultTimes.add(weektime.substring(7, 23));
		}
	}
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
}
