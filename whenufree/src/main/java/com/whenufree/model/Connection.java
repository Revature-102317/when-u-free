package com.whenufree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "Connections")
public class Connection {

	private Long connectionId;
	private User user;
	private FriendGroup friendGroup;
	private Boolean isAdmin;
	private FriendGroupStatus status;
	
	//no args constructor
	public Connection() {}

	
	@Id
	@GenericGenerator(name = "connectionsautoinc", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		      parameters = {
			  @Parameter(name = "sequence_name", value = "connectionsautoinc"),
			  @Parameter(name = "optimizer", value = "hilo"),
			  @Parameter(name = "initial_value", value = "1"),
			  @Parameter(name = "increment_size", value = "1") }
	)
	@GeneratedValue(generator = "connectionsautoinc")
	@Column(name = "connectionId")
	public Long getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(Long connectionId) {
		this.connectionId = connectionId;
	}
	
	//Getters and setters for the userid
	@ManyToOne
	@JoinColumn(name="userid", referencedColumnName="userid", nullable = false)
	//declaring foreign key
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name="friendgroupid", referencedColumnName="friendgroupid", nullable = false)
	public FriendGroup getFriendGroup() {
		return friendGroup;
	}

	public void setFriendGroup(FriendGroup friendGroup) {
		this.friendGroup = friendGroup;
	}

    @Column(name = "isadmin", nullable = false)
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status", referencedColumnName = "statusid", nullable = false)
	public FriendGroupStatus getFriendGroupStatus() {
		return status;
	}

	public void setFriendGroupStatus(FriendGroupStatus status) {
		this.status = status;
	}

	//to String method
	@Override
	public String toString() {
		return "Connection [connectionId=" + connectionId + ", userId=" + user.getUserId() + ", friendGroupId=" + friendGroup.getFriendGroupId()
				+ ", isAdmin=" + isAdmin + "]";
	}

	@Override
	public boolean equals(Object obj) {
	    if (this.getUser().equals(((Connection) obj).getUser()) && this.getFriendGroup().equals(((Connection) obj).getFriendGroup())){
		return true;
	    }
	    //return super.equals(obj);
	    return false;
	}
	
	

	
}
