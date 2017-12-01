package com.whenufree.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.whenufree.model.FriendGroup;
import com.whenufree.model.User;

@Repository
public interface FriendGroupDao extends JpaRepository<FriendGroup, Long>{
	
	//public FriendGroup save(FriendGroup f);
	
	public FriendGroup findByFriendGroupId(Long id);
	
	public FriendGroup findByName(String name);
}
