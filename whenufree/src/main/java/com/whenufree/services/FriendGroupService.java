package com.whenufree.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whenufree.dao.ConnectionDao;
import com.whenufree.dao.FriendGroupDao;
import com.whenufree.dao.FriendGroupStatusDao;
import com.whenufree.dao.GroupFreeTimeDao;
import com.whenufree.dao.UserDao;
import com.whenufree.dao.MessageDao;
import com.whenufree.model.Connection;
import com.whenufree.model.FreeTime;
import com.whenufree.model.FriendGroup;
import com.whenufree.model.FriendGroupStatus;
import com.whenufree.model.GroupFreeTime;
import com.whenufree.model.Message;
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
	
	private MessageDao messageDao;

	public FriendGroupService(){
	}
	
	@Autowired
	public FriendGroupService(UserDao userDao, FriendGroupDao friendGroupDao,
			ConnectionDao connectionDao, FriendGroupStatusDao friendGroupStatusDao,
			GroupFreeTimeDao groupFreeTimeDao, UserService userService,
			TimeSlotService timeSlotService, MessageDao messageDao){
		this.userDao = userDao;
		this.friendGroupDao = friendGroupDao;
		this.connectionDao = connectionDao;
		this.friendGroupStatusDao = friendGroupStatusDao;
		this.userService = userService;
		this.groupFreeTimeDao = groupFreeTimeDao;
		this.timeSlotService = timeSlotService;
		this.messageDao = messageDao;
	}

    public FriendGroup findById(Long id){
	return friendGroupDao.findOne(id);
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
	
	//Find a friend group by its name
	public FriendGroup findByName(String name){
		return friendGroupDao.findByName(name);
	}
	
	//Find a friend group by its id
	public FriendGroup findByFriendGroupName(String name){
		return friendGroupDao.findByName(name);
	}
	
	//Find a list of friend groups by user
	//The status must be approved
	public List<FriendGroup> findByUser(User u){
		FriendGroupStatus status = friendGroupStatusDao.findByStatusId((short) 1);
		List<Connection> connectionList = connectionDao.findByUserAndFriendGroupStatusOrderByConnectionIdAsc(u, status);
		List<FriendGroup> fgList = new ArrayList<FriendGroup>();
		for (int a = 0; a < connectionList.size(); a++){
			fgList.add(connectionList.get(a).getFriendGroup());
		}
		return fgList;
	}
	
	//adding a user to a friendgroup AKA creating a new connection
	public User addUser(FriendGroup fg, User u){
		fg.addUser(u);
		friendGroupDao.save(fg);
		return u;
	}
	
	//removing a user to a friendgroup AKA deleting a connection
		public User removeUser(FriendGroup fg, User u){
			fg.removeUser(u);
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
			List<TimeSlot> userTimeSlotList = userService.getUnScheduledFreeTimes(userList.get(a));
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
				gft.setNumUsers(timeSlotMapping.get(a));
				gft.setTimeslot(timeSlotService.findById((long) a));
				groupFreeTimeList.add(gft);
			}
		}
		return groupFreeTimeList;
	}
	
	//get a timeslot from a groupfreetime based on id
	public TimeSlot getTimeSlotByGroupFreeTime(Long id){
		GroupFreeTime freetime = groupFreeTimeDao.findByGroupFreeTimeId((long) id);
		return freetime.getTimeslot();
	}
	
	//Saves list of GroupFreeTimes Into the database
	public void saveGroupFreeTimes(List<GroupFreeTime> gft){
			groupFreeTimeDao.save(gft);
	}
	
	//does the above 3 steps:
	//getting all user free times
	//adding them into a list to the group free times database
	public void addToDatabase(FriendGroup fg){
		//We might have to add a remove all groupFreeTimes ByFriendGroup
		//Just so the groupFreeTimes reset every time we try to add something in
		List<TimeSlot> timeSlotsList = this.getAllGroupTimeSlots(fg);
		List<GroupFreeTime> groupFreeTimesList = this.timeSlotsToGroupFreeTimes(fg, timeSlotsList);
		saveGroupFreeTimes(groupFreeTimesList);
	}
	
	//returns a group free time sorted by number of users
	public List<GroupFreeTime> getGroupFreeTimes(FriendGroup fg){
		//List<GroupFreeTime> getGroupFreeTime
		return groupFreeTimeDao.findByFriendGroupOrderByNumUsersDesc(fg);
	}
	
	//deletes all group free times by friend group
	@Transactional
	public void deleteByFriendGroup(FriendGroup fg){
		groupFreeTimeDao.removeByFriendGroup(fg);
	}
	
	/**************************
	 * Messaging logic
	 */
	
    public Message sendMessage(User user, FriendGroup friendgroup, String text) {
	Message message = new Message();
	message.setAuthor(user);
	message.setFriendGroup(friendgroup);
	message.setPinned(false);
	message.setPoll(null);
	message.setText(text);
		
		
	friendgroup.sendMessage(message);
	friendGroupDao.save(friendgroup);
	return message;
    }
    
    public void save(FriendGroup group){
	friendGroupDao.save(group);
    }

	public List< Message> grabMessages( FriendGroup friendGroup) {
		return messageDao.findByFriendGroupOrderByTimestampAsc( friendGroup);
	}
}
