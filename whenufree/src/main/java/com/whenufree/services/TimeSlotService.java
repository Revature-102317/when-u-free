package com.whenufree.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whenufree.dao.TimeSlotDao;
import com.whenufree.model.TimeSlot;

@Service
public class TimeSlotService{

	private TimeSlotDao dao;
	
	@Autowired
	public TimeSlotService(TimeSlotDao dao){
		this.dao = dao;
	}
	
	public TimeSlot findById(Long i){
		return dao.findByTimeSlotId(i);
	}
	
	public List<TimeSlot> findAll(){
		return dao.findAll();
	}
	
	public List<TimeSlot> findMondays(){
		return dao.findByDateTimeStartingWith("MO");
	}
	
	public List<TimeSlot> findTuesdays(){
		return dao.findByDateTimeStartingWith("TU");
	}
	
	public List<TimeSlot> findWednesdays(){
		return dao.findByDateTimeStartingWith("WE");
	}
	
	public List<TimeSlot> findThursdays(){
		return dao.findByDateTimeStartingWith("TH");
	}
	
	public List<TimeSlot> findFridays(){
		return dao.findByDateTimeStartingWith("FR");
	}
	
	public List<TimeSlot> findSaturdays(){
		return dao.findByDateTimeStartingWith("SA");
	}
	
	public List<TimeSlot> findSundays(){
		return dao.findByDateTimeStartingWith("S");
	}
}
