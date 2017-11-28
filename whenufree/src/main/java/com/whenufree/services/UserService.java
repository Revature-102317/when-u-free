package com.whenufree.services;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;

import com.whenufree.dao.FreeTimeDao;
import com.whenufree.dao.UserDao;
import com.whenufree.model.FreeTime;
import com.whenufree.model.TimeSlot;
import com.whenufree.model.User;

@Service
public class UserService implements UserDetailsService{
    private UserDao dao;
    private FreeTimeDao freeTimeDao;

    @Autowired
    public UserService(UserDao dao, FreeTimeDao freeTimeDao){
	this.dao = dao;
	this.freeTimeDao = freeTimeDao;
    }

    public List<User> findAll(){
	return dao.findAll();
    }

    public User findByEmail(String email){
	return dao.findByEmail(email);
    }

    public User save(User u){
	return dao.save(u);
    }

    public UserDetails loadUserByUsername(String username)
	throws UsernameNotFoundException{
	User user = dao.findByEmail(username);	
	if(user == null)
	    throw new UsernameNotFoundException("Username not found");
	UserBuilder builder =
	    org.springframework.security.core.userdetails.User.withUsername(user.getEmail());
	return builder
	    .password(user.getPasswordHash())
	    .authorities("ROLE_USER")
	    .build();
    }
    
    public FreeTime setTime(User u, TimeSlot ts){
    	FreeTime ft = new FreeTime();
    	ft.setUser(u);
    	ft.setTimeSlot(ts);
    	ft.setScheduled(false);
    	ft.setIsDefault(true);
    	return freeTimeDao.save(ft);
    }
    
    public ArrayList<TimeSlot> getFreeTime(String user){
		return null;
    }
}
