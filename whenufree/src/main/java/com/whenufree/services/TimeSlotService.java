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
	
	public TimeSlot findByDateTime(String datetime){
		return dao.findByDateTime(datetime);
	}
	
	public List<TimeSlot> findAll(){
		return dao.findAllByOrderByTimeSlotIdAsc();
	}
	
	public List<TimeSlot> findMondays(){
		return dao.findByDateTimeStartingWithOrderByTimeSlotIdAsc("MO");
	}
	
	public List<TimeSlot> findTuesdays(){
		return dao.findByDateTimeStartingWithOrderByTimeSlotIdAsc("TU");
	}
	
	public List<TimeSlot> findWednesdays(){
		return dao.findByDateTimeStartingWithOrderByTimeSlotIdAsc("WE");
	}
	
	public List<TimeSlot> findThursdays(){
		return dao.findByDateTimeStartingWithOrderByTimeSlotIdAsc("TH");
	}
	
	public List<TimeSlot> findFridays(){
		return dao.findByDateTimeStartingWithOrderByTimeSlotIdAsc("FR");
	}
	
	public List<TimeSlot> findSaturdays(){
		return dao.findByDateTimeStartingWithOrderByTimeSlotIdAsc("SA");
	}
	
	public List<TimeSlot> findSundays(){
		return dao.findByDateTimeStartingWithOrderByTimeSlotIdAsc("SU");
	}
}
