package com.whenufree.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import org.hibernate.Hibernate;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;

import com.whenufree.dao.FreeTimeDao;
import com.whenufree.dao.TimeSlotDao;
import com.whenufree.dao.UserDao;
import com.whenufree.dao.ConnectionDao;
import com.whenufree.dao.MessageDao;

import com.whenufree.model.FreeTime;
import com.whenufree.model.TimeSlot;
import com.whenufree.model.User;
import com.whenufree.model.FriendsList;
import com.whenufree.model.Message;
import com.whenufree.model.Connection;
import com.whenufree.model.FriendGroup;

@Service
@EnableScheduling
public class UserService implements UserDetailsService{
    private UserDao dao;
	private ConnectionDao connectionDao;
	private MessageDao messageDao;
    private static FreeTimeDao freeTimeDao;
    private TimeSlotService timeSlotService;

    @Autowired
    public UserService(UserDao dao, FreeTimeDao freeTimeDao, TimeSlotService timeSlotService, MessageDao messageDao){
	this.dao = dao;
	this.freeTimeDao = freeTimeDao;
	this.timeSlotService = timeSlotService;
	this.connectionDao = connectionDao;
	this.messageDao = messageDao;
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

    @Override
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
    
  //This gets all the unscheduled free times of a user
    public List<TimeSlot> getUnScheduledFreeTimes(User u){
    	List<TimeSlot> timeSlotList= new ArrayList<TimeSlot>();
    	List<FreeTime> freeList = freeTimeDao.findByUserAndScheduled(u,false);
    	for(int i = 0; i < freeList.size(); i++){
    		timeSlotList.add(freeList.get(i).getTimeSlot());
    	}
    	return timeSlotList;
    }
    
  //This gets all the scheduled free times of a user
    public List<TimeSlot> getScheduledFreeTimes(User u){
    	List<TimeSlot> timeSlotList= new ArrayList<TimeSlot>();
    	List<FreeTime> freeList = freeTimeDao.findByUserAndScheduled(u,true);
    	for(int i = 0; i < freeList.size(); i++){
    		timeSlotList.add(freeList.get(i).getTimeSlot());
    	}
    	return timeSlotList;
    }
    
  //This gets all the default free times of a user
    public List<TimeSlot> getDefaultFreeTimes(User u){
    	List<TimeSlot> timeSlotList= new ArrayList<TimeSlot>();
    	List<FreeTime> freeList = freeTimeDao.findByUserAndIsDefault(u,true);
    	for(int i = 0; i < freeList.size(); i++){
    		timeSlotList.add(freeList.get(i).getTimeSlot());
    	}
    	return timeSlotList;
    }

    //This method adds the default times set by the user to the database
    public List<FreeTime> setDefaultTime(User u, List<String> weektimes){
    	for(int i = 0; i < weektimes.size(); i++){
    		FreeTime freetime = new FreeTime();
    		freetime.setIsDefault(true);
    		freetime.setScheduled(false);
    		freetime.setTimeSlot(timeSlotService.findByDateTime(weektimes.get(i)));
    		freetime.setUser(u);
    		if (!checkIfContains(u, timeSlotService.findByDateTime(weektimes.get(i)))){
    			freeTimeDao.save(freetime);
    		}else{
    			FreeTime free = freeTimeDao.getOne(freeTimeDao.findByUserAndTimeSlot(u, freetime.getTimeSlot()).getFreeTimeId());
    			free.setIsDefault(true);
    			freeTimeDao.save(free);
    		}
    	}
    	return freeTimeDao.findByUser(u);
    }
    
    //setting scheduled times
    public List<FreeTime> setScheduledTime(User u, List<String> weektimes){
    	for(int i = 0; i < weektimes.size(); i++){
    		FreeTime freetime = new FreeTime();
    		freetime.setIsDefault(false);
    		freetime.setScheduled(true);
    		freetime.setTimeSlot(timeSlotService.findByDateTime(weektimes.get(i)));
    		freetime.setUser(u);
    		if (!checkIfContains(u, timeSlotService.findByDateTime(weektimes.get(i)))){
    			freeTimeDao.save(freetime);
    		} else{
    			FreeTime free = freeTimeDao.getOne(freeTimeDao.findByUserAndTimeSlot(u, freetime.getTimeSlot()).getFreeTimeId());
    			free.setScheduled(true);
    			freeTimeDao.save(free);
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
    
    @Transactional
    public List<FreeTime> deleteDefaultWithoutTouchingScheduled(User u){
    	List<FreeTime> ft = freeTimeDao.findByUser(u);
    	for(int a = 0; a < ft.size(); a++){
    		FreeTime free = freeTimeDao.findByUserAndTimeSlot(ft.get(a).getUser(), ft.get(a).getTimeSlot());
    		FreeTime freetime = freeTimeDao.getOne(free.getFreeTimeId());
    		freetime.setIsDefault(false);
    		freeTimeDao.save(freetime);
    	}
    	return freeTimeDao.removeByUserAndScheduled(u, false);
    }
    
    @Transactional
    public List<FreeTime> deleteScheduledWithoutTouchingDefault(User u){
    	List<FreeTime> ft = freeTimeDao.findByUser(u);
    	for(int a = 0; a < ft.size(); a++){
    		FreeTime free = freeTimeDao.findByUserAndTimeSlot(ft.get(a).getUser(), ft.get(a).getTimeSlot());
    		FreeTime freetime = freeTimeDao.getOne(free.getFreeTimeId());
    		freetime.setScheduled(false);
    		freeTimeDao.save(freetime);
    	}
    	return freeTimeDao.removeByUserAndIsDefault(u, false);
    }
    
    public ArrayList<TimeSlot> getFreeTime(String user){
		return null;
    }

	public User save(User u){
		return dao.save(u);
    }

	public Set< Message> getAllMessagesByUser( User u) {
		return messageDao.findByAuthor(u);
	}

	public void deleteUser(User u) {
		User deleted = dao.findByUserId( 999999999999999999L);

		Set<FriendsList> friendsList = u.getFriendsList();
		Iterator<FriendsList> iterOfFriendsList = friendsList.iterator();

		Set<User> listOfFriends = new HashSet<>();
		Iterator<User> iterOfFriends = listOfFriends.iterator();

		Set<Connection> groupList = u.getConnections();
		Iterator<Connection> iterOfConnections = groupList.iterator();

		Set<FreeTime> freeTimes = u.getFreeTimes();
		Iterator<FreeTime> iterOfFreeTimes = freeTimes.iterator();

		Set< Message> allMessages = new HashSet<>();
		if(this.getAllMessagesByUser(u) != null )
			allMessages = this.getAllMessagesByUser(u);

		Iterator< Message> iterOfMessages = allMessages.iterator();

		// This deletes all free time entries from the user
		this.deleteByUser(u);

		// This sets author to generic deleted user
		while( iterOfMessages.hasNext()) {
			Message current = iterOfMessages.next();
			current.setAuthor( deleted);
		}

		while( iterOfConnections.hasNext()) {
			FriendGroup current = iterOfConnections.next().getFriendGroup();
				current.removeUser(u);
		}

		while( iterOfFriendsList.hasNext()) {
			FriendsList current = iterOfFriendsList.next();
			Long friendId = current.getFriendsListPK().getFriendId();
			listOfFriends.add( dao.findByUserId(friendId));
		}

		while( iterOfFriends.hasNext()) {
			u.removeFriend( iterOfFriends.next());
		}
		
		dao.delete(u);
	}

	/*
    //cron = "1 0 0 * * SUN"
    @Scheduled(cron = "1 0 0 * * SUN")
    public void reset(){
	System.out.println("Refreshing Everything!");
	List<FreeTime> freetimes = freeTimeDao.findAll();
	for(FreeTime ft : freetimes){
	    if(ft.getIsDefault()){
		ft.setScheduled(false);
		freeTimeDao.save(ft);
	    } else{
		freeTimeDao.delete(ft);
	    }
	}
	System.out.println("Done Refreshing");
    }
	*/
}
