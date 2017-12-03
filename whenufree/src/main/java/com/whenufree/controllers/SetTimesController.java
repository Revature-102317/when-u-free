package com.whenufree.controllers;

import java.security.Principal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import javax.inject.Provider;

@Controller
public class SetTimesController {
	
	private UserService userService;
	
	private TimeSlotService timeSlotService;
	
	@Autowired
	private Provider<List<String>> defaultTimesProvider;
	
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
		return new ResponseEntity<List<String>>(defaultTimesProvider.get(), HttpStatus.OK);
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
	@SuppressWarnings("rawtypes")
	@RequestMapping(path="/setdefaulttime", method= RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity setdefaulttime(@RequestBody String weektime, Principal user){
	List<String> defaultTimes = defaultTimesProvider.get();
	User u = userService.findByEmail(user.getName());
	//checks to make sure the content is not refreshed
	System.out.println("receiving " + weektime);
	if (weektime.substring(7, 13).equals("delete")){
		defaultTimes.removeAll(defaultTimes);
		System.out.println("deleted " + weektime.substring(7, 13));
		return null;
	}
	if (weektime.substring(11, 17).equals("submit")){
		userService.deleteByUser(u);
		userService.setDefaultTime(u, defaultTimes);
		
//		Set d = u.getFreeTimes();
//		Set s = new HashSet<>();
//		for(time : defaultTimes){
//			dbTime = new FreeTime(u, TimeSlotService.getByDateTime(time));
//			//populate dbTime with time
//			for(time2: d){
//				if(time2.equals(dbTime)){
//					dbTime.setFreeTimeId(time2.getFreeTimeId());
//				}	
//			}
//			s.add(dbTime);
//		}
//		u.setFreeTimes(s);
//		
//		userService.save(u);
		
		defaultTimes.removeAll(defaultTimes);
		System.out.println("submitted " + weektime.substring(11, 17));
	} else if (weektime.substring(23, 27).equals("true")){
		defaultTimes.remove(weektime.substring(7, 23));
		System.out.println("removing "+ weektime.substring(7, 23));
	} else if (weektime.substring(23, 27).equals("fals")){
		if(defaultTimes.contains(weektime.substring(7, 23))){
			System.out.println("tried to add but already contains");
		} else {
			defaultTimes.add(weektime.substring(7, 23));
			System.out.println("adding "+ weektime.substring(7, 23));
		}
	}
	
	//Old Code
	//This removes the post request from the list(not database) if the list already has it
	/*
	if (defaultTimes.contains(weektime.substring(7, 23))){
		defaultTimes.remove(weektime.substring(7, 23));
		System.out.println("removing "+ weektime.substring(7, 23));
	}
		else {
		//This method submits the list to the database
		if (weektime.substring(11, 17).equals("submit")){
			userService.deleteByUser(u);
			userService.setDefaultTime(u, defaultTimes);
			defaultTimes.removeAll(defaultTimes);
			System.out.println("submitted " + weektime.substring(11, 17));
		}
			//This method adds the post request to the list(not database)
		else{
			defaultTimes.add(weektime.substring(7, 23));
			System.out.println("adding "+ weektime.substring(7, 23));
		}
	}*/
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
}
