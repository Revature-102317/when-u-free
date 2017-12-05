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
	
	public List<FreeTime> findByUserAndScheduled(User u, boolean b);
	
	public FreeTime findByUserAndTimeSlot(User u, TimeSlot t);
	
	public List<FreeTime> removeByUser(User u);

	public List<FreeTime> removeByUserAndScheduled(User u, boolean b);

	public List<FreeTime> removeByUserAndIsDefault(User u, boolean b);

	public List<FreeTime> findByUserAndIsDefault(User u, boolean b);
	
}
