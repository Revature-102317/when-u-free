package com.whenufree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Connection")
public class Connection {

	private Long connectionId;
	private User user;
	private FriendGroup friendGroup;
	private short isAdmin;
	
	//no args constructor
	public Connection() {}

	@Id
	@Column(name = "connectionId")
	public Long getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(Long connectionId) {
		this.connectionId = connectionId;
	}
	
	//Getters and setters for the userid
	@ManyToOne
	@JoinColumn(name="userid", referencedColumnName="userid")
	//declaring foreign key
	public User getUserId() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name="friendgroupid", referencedColumnName="friendgroupid")
	public FriendGroup getFriendGroup() {
		return friendGroup;
	}

	public void setFriendGroup(FriendGroup friendGroup) {
		this.friendGroup = friendGroup;
	}

	@Column(name = "isadmin")
	public short getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(short isAdmin) {
		this.isAdmin = isAdmin;
	}

	//to String method
	@Override
	public String toString() {
		return "Connection [connectionId=" + connectionId + ", userId=" + user.getUserId() + ", friendGroupId=" + friendGroup.getFriendGroupId()
				+ ", isAdmin=" + isAdmin + "]";
	}

	
}
