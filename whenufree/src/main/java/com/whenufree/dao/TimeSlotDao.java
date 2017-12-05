package com.whenufree.dao;

import java.util.ArrayList;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whenufree.model.TimeSlot;

@Repository
public interface TimeSlotDao extends JpaRepository<TimeSlot, Long>{
	
	public TimeSlot findByTimeSlotId(Long id);
	
	public List<TimeSlot> findAllByOrderByTimeSlotIdAsc();
	
	public List<TimeSlot> findByDateTimeStartingWithOrderByTimeSlotIdAsc(String week);

	public TimeSlot findByDateTime(String datetime);
}
