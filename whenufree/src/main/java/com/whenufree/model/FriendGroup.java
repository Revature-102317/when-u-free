package com.whenufree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="friendgroup")
public class FriendGroup {

    private long friendGroupId;
    private String name;
	
    //no args constructor
    public FriendGroup() {}
	
    //getters and setters for the friend group id
    @Id
    @Column(name = "friendgroupid")
    public long getFriendGroupId() {
	return friendGroupId;
    }
    public void setFriendGroupId(long friendGroupId) {
	this.friendGroupId = friendGroupId;
    }
	
    //getters and setters for the name of the friend group
    @Column(name = "name")
    public String getName() {
	return name;
    }
    public void setName(String name) {
	this.name = name;
    }

    //to String method
    @Override
    public String toString() {
	return "FriendGroup [friendGroupId=" + friendGroupId + ", name=" + name + "]";
    }

	
}
