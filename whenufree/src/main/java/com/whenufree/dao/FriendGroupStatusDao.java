package com.whenufree.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whenufree.model.FriendGroupStatus;

@Repository
public interface FriendGroupStatusDao extends JpaRepository<FriendGroupStatus, Long>{
	
	public FriendGroupStatus findByStatusId(Short s);

}
