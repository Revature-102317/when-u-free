package com.whenufree.services;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.Hibernate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;

import com.whenufree.dao.FreeTimeDao;
import com.whenufree.dao.TimeSlotDao;
import com.whenufree.dao.UserDao;
import com.whenufree.model.FreeTime;
import com.whenufree.model.TimeSlot;
import com.whenufree.model.User;
import com.whenufree.model.FriendsList;

@Service
public class UserService implements UserDetailsService{
    private UserDao dao;
    private static FreeTimeDao freeTimeDao;
    private TimeSlotService timeSlotService;

    @Autowired
    public UserService(UserDao dao, FreeTimeDao freeTimeDao, TimeSlotService timeSlotService){
	this.dao = dao;
	this.freeTimeDao = freeTimeDao;
	this.timeSlotService = timeSlotService;
    }

    public List<User> findAll(){
	return dao.findAll();
    }
    
    public User findByUserId(Long id){
    	return dao.findByUserId(id);
    }

    public User findByEmail(String email){
	return dao.findByEmail(email);
    }

    @Transactional
    public User findByEmailAndInitializeFriendsList(String email){
	User u = dao.findByEmail(email);
	Hibernate.initialize(u.getFriendsList());
	System.out.println(1);
	System.out.println(u.getFriendsList().getClass());
	
	for(FriendsList f : u.getFriendsList())
	    System.out.println(f);
	return u;
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
    
    //The below Methods are supposed to help the user set their times
    
    //This gets all the free times of a user
    public List<TimeSlot> getFreeTimesByUser(User u){
    	List<TimeSlot> timeSlotList= new ArrayList<TimeSlot>();
    	List<FreeTime> freeList = freeTimeDao.findByUser(u);
    	for(int i = 0; i < freeList.size(); i++){
    		timeSlotList.add(freeList.get(i).getTimeSlot());
    	}
    	return timeSlotList;
    }

    //This method adds the default times set by the user to the database
    public List<FreeTime> setDefaultTime(User u, List<String> weektimes){
    	List<FreeTime> ft = new ArrayList<FreeTime>();
    	for(int i = 0; i < weektimes.size(); i++){
    		FreeTime freetime = new FreeTime();
    		freetime.setIsDefault(true);
    		freetime.setScheduled(false);
    		freetime.setTimeSlot(timeSlotService.findByDateTime(weektimes.get(i)));
    		freetime.setUser(u);
    		if (!checkIfContains(u, timeSlotService.findByDateTime(weektimes.get(i)))){
    			freeTimeDao.save(freetime);
    		}
    	}
    	return freeTimeDao.findByUser(u);
    }
    
    //This method checks if the freetime container already contains the said times
    //This makes sure to not add duplicate times
    public static Boolean checkIfContains(User u, TimeSlot t){
    	FreeTime freetime = freeTimeDao.findByUserAndTimeSlot(u, t);
    	if (freetime == null){
    		return false;
    	}
    	return true;
    }
    
    //This deletes all free time entries by a given user
    @Transactional
    public List<FreeTime> deleteByUser(User u){
    	return freeTimeDao.removeByUser(u);
    }
    
    public ArrayList<TimeSlot> getFreeTime(String user){
		return null;
    }
}
