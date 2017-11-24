package com.whenufree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Connections")
public class Connection {

	private Long connectionId;
	private User user;
	private FriendGroup friendGroup;
	private Short isAdmin;
	private Short Status;
	
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
	public Short getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Short isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	@ManyToOne
	@JoinColumn(name="status", referencedColumnName="statusId")
	public Short getStatus() {
		return Status;
	}

	public void setStatus(Short status) {
		this.Status = status;
	}

	//to String method
	@Override
	public String toString() {
		return "Connection [connectionId=" + connectionId + ", userId=" + user.getUserId() + ", friendGroupId=" + friendGroup.getFriendGroupId()
				+ ", isAdmin=" + isAdmin + ", Status=" + Status + "]";
	}

	
}
