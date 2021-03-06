package com.whenufree.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whenufree.model.FriendGroup;
import com.whenufree.model.Message;
import com.whenufree.model.User;

import java.util.Set;
import java.util.HashSet;

@Repository
public interface MessageDao extends JpaRepository<Message, Long> {
	
	public List< Message> findByFriendGroupOrderByTimestampAsc( FriendGroup friendGroup);
	public Set< Message> findByAuthor( User user);

}
