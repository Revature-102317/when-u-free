package com.whenufree.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whenufree.dao.ConnectionDao;
import com.whenufree.dao.FriendGroupDao;
import com.whenufree.dao.FriendGroupStatusDao;
import com.whenufree.dao.GroupFreeTimeDao;
import com.whenufree.dao.UserDao;
import com.whenufree.model.Connection;
import com.whenufree.model.FreeTime;
import com.whenufree.model.FriendGroup;
import com.whenufree.model.FriendGroupStatus;
import com.whenufree.model.GroupFreeTime;
import com.whenufree.model.TimeSlot;
import com.whenufree.model.User;

@Service
public class FriendGroupService {
	
	private UserDao userDao;
	
	private UserService userService;
	
	private TimeSlotService timeSlotService;
	
	private FriendGroupDao friendGroupDao;
	
	private ConnectionDao connectionDao;
	
	private FriendGroupStatusDao friendGroupStatusDao;
	
	private GroupFreeTimeDao groupFreeTimeDao;
	
	public FriendGroupService(){
	}
	
	@Autowired
	public FriendGroupService(UserDao userDao, FriendGroupDao friendGroupDao,
			ConnectionDao connectionDao, FriendGroupStatusDao friendGroupStatusDao,
			GroupFreeTimeDao groupFreeTimeDao, UserService userService,
			TimeSlotService timeSlotService){
		this.userDao = userDao;
		this.friendGroupDao = friendGroupDao;
		this.connectionDao = connectionDao;
		this.friendGroupStatusDao = friendGroupStatusDao;
		this.userService = userService;
		this.groupFreeTimeDao = groupFreeTimeDao;
		this.timeSlotService = timeSlotService;
	}
	
	//Find a status by its id
	public FriendGroupStatus findByStatusId(Short id){
		return friendGroupStatusDao.findByStatusId(id);
	}
	
	//Adding new FriendGroup
	public FriendGroup createFriendGroup(String name){
		FriendGroup fg = new FriendGroup();
		fg.setName(name);
		return friendGroupDao.save(fg);
	}
	
	//Deleting a FriendGroup
	public void deleteFriendGroup(Long id){
		friendGroupDao.delete(id);
	}

	//Find a friend group by its id
	public FriendGroup findByFriendGroupId(Long id){
		return friendGroupDao.findByFriendGroupId(id);
	}
	
	//adding a user to a friendgroup AKA creating a new connection
	public User addUser(FriendGroup fg, User u){
		fg.addUser(u);
		friendGroupDao.save(fg);
		return u;
	}
	
	//getting all users in a friendgroup by user ID
	public List<User> findUsers(FriendGroup fg){
		Set<Connection> connectionList = fg.getConnections();
		List<User> userList = new ArrayList<User>();
		Iterator<Connection> it = connectionList.iterator();
	     while(it.hasNext()){
	        userList.add(it.next().getUser());
	     }
		return userList;
	}
	
	//CORE LOGIC
	//GETTING FREE TIMES OF ALL USERS IN A FRIEND GROUP
	/**********************************************************************/
	//gets free times of all users and pushes them on a timeslot list
	public List<TimeSlot> getAllGroupTimeSlots(FriendGroup fg){
		List<User> userList = this.findUsers(fg);
		List<TimeSlot> timeSlotList = new ArrayList<TimeSlot>();
		for(int a = 0; a < userList.size(); a++){
			List<TimeSlot> userTimeSlotList = userService.getFreeTimesByUser(userList.get(a));
			timeSlotList.addAll(userTimeSlotList);
		}
		return timeSlotList;
	}
	
	//converts a list of time slots to a list of group free times
	public List<GroupFreeTime> timeSlotsToGroupFreeTimes(FriendGroup fg, List<TimeSlot> tsList){
		List<GroupFreeTime> groupFreeTimeList = new ArrayList<GroupFreeTime>();
		List<Integer> timeSlotMapping = new ArrayList<>();
		for(int a = 0; a < 169; a++){
			timeSlotMapping.add((Integer) 0);
		}
		for(int a = 0; a < tsList.size(); a++){
			int timeSlotId = (tsList.get(a).getTimeSlotId()).intValue();
			Integer i = new Integer(timeSlotMapping.get(timeSlotId).intValue() + 1);
			timeSlotMapping.set(timeSlotId, i);
		}
		for(int a = 0; a < 169; a++){
			if(!timeSlotMapping.get(a).equals(0)){
				GroupFreeTime gft = new GroupFreeTime();
				gft.setFriendGroup(fg);
				gft.setGroupFreeTimeId((long) a);
				gft.setNumUsers(timeSlotMapping.get(a));
				gft.setTimeslot(timeSlotService.findById((long) a));
				groupFreeTimeList.add(gft);
			}
		}
		return groupFreeTimeList;
	}
	
	//Saves list of GroupFreeTimes Into the database
	public List<GroupFreeTime> saveGroupFreeTimes(List<GroupFreeTime> gft){
		groupFreeTimeDao.save(gft);
		return gft;
	}
	
	//returns a group free time sorted by number of users
	public List<GroupFreeTime> getGroupFreeTimes(FriendGroup fg){
		//List<GroupFreeTime> getGroupFreeTime
		return groupFreeTimeDao.findByFriendGroupOrderByNumUsersDesc(fg);
	}
	
}
