package com.whenufree.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whenufree.model.Connection;

@Repository
public interface ConnectionDao extends JpaRepository<Connection, Long>{
	
	public Connection save(Connection c);

}
