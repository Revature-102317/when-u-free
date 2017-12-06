package com.whenufree.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whenufree.jsonpojos.MessageJson;
import com.whenufree.jsonpojos.Named;
import com.whenufree.jsonpojos.UserJson;
import com.whenufree.model.Connection;
import com.whenufree.model.FriendGroup;
import com.whenufree.model.GroupFreeTime;
import com.whenufree.model.GroupFreeTimeComparator;
import com.whenufree.model.Message;
import com.whenufree.model.TimeSlot;
import com.whenufree.model.User;
import com.whenufree.services.FriendGroupService;
import com.whenufree.services.TimeSlotService;
import com.whenufree.services.UserService;
import javax.inject.Provider;

@Controller
public class FriendGroupController {
	
	private UserService userService;
	
	private TimeSlotService timeSlotService;
	
	private FriendGroupService friendGroupService;
	
	@Autowired
	private Provider<FriendGroup> activeFriendGroup;
	
	@Autowired
	public FriendGroupController(UserService userService, TimeSlotService timeSlotService, FriendGroupService friendGroupService){
		this.userService = userService;
		this.timeSlotService = timeSlotService;
		this.friendGroupService = friendGroupService;
	}
	
	//path that returns friend groups of the current user
	@RequestMapping(path="/myfriendgroups", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<FriendGroup>> getAll(Principal user){
		User u = userService.findByEmail(user.getName());
		List<FriendGroup> fgList = friendGroupService.findByUser(u);
		for (int a = 0; a < fgList.size(); a++){
			fgList.get(a).getConnections().removeAll(fgList.get(a).getConnections());
			fgList.get(a).getMessages().removeAll(fgList.get(a).getMessages());
		}
		return new ResponseEntity<List<FriendGroup>>(fgList, HttpStatus.OK);
	}
	
	//path that returns the friendgroup that was clicked on
		@RequestMapping(path="/friendgroup", method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<FriendGroup> getFriendGroup(@PathVariable("i") Long id){
			/*
			FriendGroup fg = friendGroupService.findByName(activeFriendGroup.get().getName());
			fg.getConnections().removeAll(fg.getConnections());*/
			FriendGroup fg = friendGroupService.findByFriendGroupId(id);
			fg.getConnections().removeAll(fg.getConnections());
			fg.getMessages().removeAll(fg.getMessages());
			return new ResponseEntity<FriendGroup>(fg, HttpStatus.OK);
		}
		
	//path that returns the list of users in the friend group
		@RequestMapping(path="/friendgroupusers/{i}", method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<List<UserJson>> getFriendGroupUsers(@PathVariable("i") Long id){
			FriendGroup fg = friendGroupService.findByFriendGroupId(id);
			List<UserJson> users = new ArrayList<UserJson>();
			//connections of that friend group
			Set<Connection> connections = fg.getConnections();
			//list of approved connections
			Set<Connection> approvedConnections= new HashSet<Connection>();
			
			//This loop creates a list of approved connections
			Iterator<Connection> i = connections.iterator();
			int counter = 0;
			while(i.hasNext()){
				Connection c = i.next();
				if(c.getFriendGroupStatus().getStatusId() == 1){
					approvedConnections.add(c);
				}
			}
			
			//This generates a list of users that are approved in that list
			Iterator<Connection> it = approvedConnections.iterator();
			int count = 0;
			while(it.hasNext()){
				UserJson ujson = new UserJson(it.next().getUser());
		        users.add(ujson);
		        users.get(count).getConnections().removeAll(users.get(count).getConnections());
		        users.get(count).getFriendsList().removeAll(users.get(count).getFriendsList());
		        count++;
		     }
			return new ResponseEntity<List<UserJson>>(users, HttpStatus.OK);
		}
		
    @RequestMapping(path = "/getactivefriendgroup/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<FriendGroup> getFriendGroupById(@PathVariable("id") Long id){
	FriendGroup fg = friendGroupService.findByFriendGroupId(id);
	fg.getConnections().removeAll(fg.getConnections());
	fg.getMessages().removeAll(fg.getMessages());
	return new ResponseEntity<>(fg, HttpStatus.OK);
    }
	
	//path the post request of gotten friend group was sent to
	@RequestMapping(path="/clickedfriendgroup", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> receiveClickedOnFriendGroup(@RequestBody String id){
		//this is returned {"friendgroup":"name"}
		//We will now substring this
		System.out.println(id);
		System.out.println(id.substring(15, id.length()-1));
		FriendGroup fg = activeFriendGroup.get();
		Integer fgId = Integer.parseInt(id.substring(15, id.length()-1));
		FriendGroup fg2 = friendGroupService.findByFriendGroupId((long) fgId);
		fg.setConnections(fg2.getConnections());
		fg.setFriendGroupId(fg2.getFriendGroupId());
		fg.setName(fg2.getName());
		friendGroupService.deleteByFriendGroup(fg2);
		friendGroupService.addToDatabase(fg2);
		System.out.println(activeFriendGroup.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//path that returns the group free times of the current friend group
		@RequestMapping(path="/groupfreetimes/{i}", method=RequestMethod.GET)
		@ResponseBody
		public ResponseEntity<List<GroupFreeTime>> getGroupFreeTimes(@PathVariable("i") Long id){
			FriendGroup fg = friendGroupService.findByFriendGroupId(id);
			friendGroupService.deleteByFriendGroup(fg);
			friendGroupService.addToDatabase(fg);
			List<GroupFreeTime> gft = friendGroupService.getGroupFreeTimes(fg);
			GroupFreeTimeComparator gftCompare = new GroupFreeTimeComparator();
			gft.sort(gftCompare);
			//whileloop
			//while loop
			//++1
			//if consecutive
			//collapse
			//endwhile
			//i++
			//endwhile
			for(int a = 0; a < gft.size(); a++){
				gft.get(a).getFriendGroup().getConnections().removeAll(gft.get(a).getFriendGroup().getConnections());
				gft.get(a).getFriendGroup().getMessages().removeAll(gft.get(a).getFriendGroup().getMessages());
			}
			return new ResponseEntity<List<GroupFreeTime>>(gft, HttpStatus.OK);
		}
	
	//path that returns the group free times of the current friend group but better
	@RequestMapping(path="/groupfreetimesbetter/{i}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Named>> getGroupFreeTimesBetter(@PathVariable("i") Long id){
		FriendGroup fg = friendGroupService.findByFriendGroupId(id);
		List<GroupFreeTime> gft = friendGroupService.getGroupFreeTimes(fg);
		GroupFreeTimeComparator gftCompare = new GroupFreeTimeComparator();
		gft.sort(gftCompare);
		List<Named> dateTimeList = new ArrayList<Named>();
		for(int a = 0; a < gft.size(); a++){
			StringBuilder sb = new StringBuilder();
			if(gft.size()>1){
				sb.append(gft.get(a).getTimeslot().getDateTime().substring(3, 16));
				//StringBuilder opening = new StringBuilder();
				//opening.append(gft.get(a).getTimeslot().getDateTime().substring(3, 11));
				//StringBuilder ending = new StringBuilder();
				//ending.append(gft.get(a).getTimeslot().getDateTime().substring(11, 16));
				for(; a < gft.size(); a++){
					if(gft.size() > a+1 
							&& gft.get(a).getTimeslot().getTimeSlotId().intValue() == gft.get(a+1).getTimeslot().getTimeSlotId().intValue()-1
							&& gft.get(a).getTimeslot().getDateTime().substring(0, 2).equals(gft.get(a+1).getTimeslot().getDateTime().substring(0, 2))
							&& gft.get(a).getNumUsers().equals(gft.get(a+1).getNumUsers())){
						sb.delete(8,  13);
						sb.append(gft.get(a+1).getTimeslot().getDateTime().substring(11, 16));
					}else{
						break;
					}
				}
				
				switch(gft.get(a).getTimeslot().getDateTime().substring(0, 2)){
				case "SU": sb.insert(0, "Sunday: ");
				break;
				case "MO": sb.insert(0, "Monday: ");
				break;
				case "TU": sb.insert(0, "Tuesday: ");
				break;
				case "WE": sb.insert(0, "Wednesday: ");
				break;
				case "TH": sb.insert(0, "Thursday: ");
				break;
				case "FR": sb.insert(0, "Friday: ");
				break;
				case "SA": sb.insert(0, "Saturday: ");
				break;
				}
				Named named = new Named();
				named.setId(gft.get(a).getNumUsers().longValue());
				named.setName(sb.toString());
				named.setClassName("greatTimes");
				dateTimeList.add(named);
			}
		}
		//whileloop
		//while loop
		//++1
		//if consecutive
		//collapse
		//endwhile
		//i++
		//endwhile
		for(int a = 0; a < gft.size(); a++){
			gft.get(a).getFriendGroup().getConnections().removeAll(gft.get(a).getFriendGroup().getConnections());
		}
		return new ResponseEntity<List<Named>>(dateTimeList, HttpStatus.OK);
	}
	
	@RequestMapping(path="/createfriendgroup", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> createFriendGroup(@RequestBody String message, Principal user){
		//The 3 in the below statement should be exchanged for the json for substringing
		User u = userService.findByEmail(user.getName());
		String fgName = message.substring(9, message.length()-2);
		friendGroupService.createFriendGroup(u, fgName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	/*************************************************
	 * 
	 * Messaging stuff
	 * 
	 */
	
	//Returns a list of messages in the friendgroup
	@RequestMapping(path="/friendgroupmessages", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<MessageJson>> getFriendGroupMessages(){
		FriendGroup fg = friendGroupService.findByFriendGroupId(activeFriendGroup.get().getFriendGroupId());
		List<MessageJson> sent = new ArrayList<MessageJson>();
		//connections of that friend group
		Set<Message> messages = fg.getMessages();
		Iterator<Message> it = messages.iterator();
		while(it.hasNext()){
			Message m = it.next();
	        MessageJson mj = new MessageJson(m);
	        sent.add(mj);
	     }
		return new ResponseEntity<List<MessageJson>>(sent, HttpStatus.OK);
	}
	
	//path the post request of gotten friend group was sent to
    @RequestMapping(path="/sendmessage", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> sendMessage(@RequestBody String message, Principal user){
	//The 3 in the below statement should be exchanged for the json for substringing
	User u = userService.findByEmail(user.getName());
	FriendGroup fg = friendGroupService.findByFriendGroupId(activeFriendGroup.get().getFriendGroupId());
	String m = message.substring(3 ,message.length()-2);
	friendGroupService.sendMessage(u, fg, m);
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path="/removeuser/{id}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity removeUser(@PathVariable("id") Long groupId,  Named user){
	FriendGroup fg = friendGroupService.findById(groupId);
	User u = userService.findByUserId(user.getId());
	fg.removeUser(u);
	friendGroupService.save(fg);
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(path="/inviteuser/{id}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity inviteUser(@PathVariable("id") Long groupId,  Named user){
	FriendGroup fg = friendGroupService.findById(groupId);
	User u = userService.findByUserId(user.getId());
	fg.inviteUser(u);
	friendGroupService.save(fg);
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path="/approveuser/{id}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity approveUser(@PathVariable("id") Long groupId,  Named user){
	FriendGroup fg = friendGroupService.findById(groupId);
	User u = userService.findByUserId(user.getId());
	fg.approveUser(u);
	friendGroupService.save(fg);
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(path="/getapplied/{id}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Named>> getApplied(@PathVariable("id") Long groupId){
	FriendGroup fg = friendGroupService.findById(groupId);
	List<Named> l = new ArrayList<>();
	for(Connection c : fg.getConnections()){
	    if(c.getFriendGroupStatus().getStatusName().equals("applied")){
		l.add(new Named(c.getUser()));
	    }
	}
	return new ResponseEntity<>(l, HttpStatus.OK);
    }
    
}
