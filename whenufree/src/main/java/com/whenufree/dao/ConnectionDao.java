package com.whenufree.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whenufree.model.Connection;
import com.whenufree.model.FriendGroupStatus;
import com.whenufree.model.User;

@Repository
public interface ConnectionDao extends JpaRepository<Connection, Long>{
	
	public Connection save(Connection c);
	
	public List<Connection> findByUser(User u);
	
	public List<Connection> findByUserOrderByConnectionIdAsc(User u);
	
	public List<Connection> findByUserAndFriendGroupStatusOrderByConnectionIdAsc(User u, FriendGroupStatus fgs);
}
