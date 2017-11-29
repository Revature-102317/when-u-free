package com.whenufree.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whenufree.model.FreeTime;
import com.whenufree.model.TimeSlot;
import com.whenufree.model.User;

@Repository
public interface FreeTimeDao extends JpaRepository<FreeTime, Long>{

	public FreeTime save(FreeTime ft); 
	
	public List<FreeTime> findByUser(User u);
	
	public FreeTime findByUserAndTimeSlot(User u, TimeSlot t);
}
