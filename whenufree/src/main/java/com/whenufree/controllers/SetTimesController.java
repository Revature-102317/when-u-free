package com.whenufree.controllers;

import java.util.List;

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
import com.whenufree.services.TimeSlotService;
import com.whenufree.services.UserService;

@Controller
public class SetTimesController {
	
	private UserService userService;
	
	private TimeSlotService timeSlotService;
	
	private String currId;
	
	@Autowired
	public SetTimesController(UserService userService, TimeSlotService timeSlotService){
		this.userService = userService;
		this.timeSlotService = timeSlotService;
	}
	
	@RequestMapping(path="/timeslots", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<TimeSlot>> getAll(){
		
		List<TimeSlot> t = timeSlotService.findAll();
		
		return new ResponseEntity<List<TimeSlot>>(t, HttpStatus.OK);
	}
	
	@RequestMapping(path="/timeslot/{id}", method={RequestMethod.GET, RequestMethod.POST},
			consumes="*/*", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<TimeSlot> getOne(@PathVariable("id") Long x){
		
		TimeSlot t = timeSlotService.findById(x);
		
		return new ResponseEntity<TimeSlot>(t, HttpStatus.OK);
	}
	
	@RequestMapping(path="/defaulttime", method={RequestMethod.GET, RequestMethod.POST},
			consumes="*/*", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getWeekTime(){
		return currId;
	}
	
	@RequestMapping(path="/setdefaulttime", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity setdefaulttime(@RequestBody String weektime){
	currId = weektime;
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
}
