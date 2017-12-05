package com.whenufree.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whenufree.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
	
    public User findByEmail(String email);
    
    public User findByUserId(Long id);
    
}
