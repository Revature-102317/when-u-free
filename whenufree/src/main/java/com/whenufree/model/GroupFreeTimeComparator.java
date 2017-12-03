package com.whenufree.model;

import com.whenufree.model.GroupFreeTime;

import java.util.Comparator;

public class GroupFreeTimeComparator implements Comparator<GroupFreeTime>{

    @Override
    public int compare(GroupFreeTime o1, GroupFreeTime o2){
	int ret = 0;
	//Order by number of free users, descending
	ret = o2.getNumUsers() - o1.getNumUsers();
	//If number of users are the same, order by timeslot, ascending
	if(ret == 0){
	    long diff = o1.getTimeslot().getTimeSlotId() - o2.getTimeslot().getTimeSlotId();
	    if(diff > 0){
		ret = 1;
	    } else if(diff < 0){
		ret = -1;
	    } else{
		ret = 0;
	    }
	}
	return ret;
    }
    
}
