package com.whenufree.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whenufree.model.FriendGroup;
import com.whenufree.model.GroupFreeTime;

@Repository
public interface GroupFreeTimeDao extends JpaRepository<GroupFreeTime, Long>{

	public GroupFreeTime save(GroupFreeTime gft);
	
	public void delete(GroupFreeTime gtf);
	
	public List<GroupFreeTime> findByFriendGroupOrderByNumUsersDesc(FriendGroup fg);
	
	public GroupFreeTime findByGroupFreeTimeId(Long id);
	
}
