package com.whenufree.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whenufree.model.FreeTime;

@Repository
public interface FreeTimeDao extends JpaRepository<FreeTime, Long>{

	public FreeTime save(FreeTime ft); 
	
}
