package com.whenufree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Connection")
public class Connection {

	private Long connectionId;
	private long userId;
	private long friendGroupId;
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
	@OneToMany
	@JoinColumn(name="userid", referencedColumnName="userid")
	//declaring foreign key
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@OneToMany
	@JoinColumn(name="friendgroupid", referencedColumnName="friendgroupid")
	public long getFriendGroupId() {
		return friendGroupId;
	}

	public void setFriendGroupId(long friendGroupId) {
		this.friendGroupId = friendGroupId;
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
		return "Connection [connectionId=" + connectionId + ", userId=" + userId + ", friendGroupId=" + friendGroupId
				+ ", isAdmin=" + isAdmin + "]";
	}

	
}
