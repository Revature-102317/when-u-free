package com.whenufree.model;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;
import org.springframework.beans.factory.annotation.Autowired;

import com.whenufree.dao.FriendGroupStatusDao;
import com.whenufree.services.FriendGroupService;

import org.hibernate.search.annotations.Field;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Indexed
@Entity
@Table(name="friendgroup", indexes = {@Index(columnList = "name")})
public class FriendGroup {
	
	

    private Long friendGroupId;
    private String name;
	
    private Set<Connection> connections;
    private Set<Message> messages;
    
    //no args constructor
    public FriendGroup() {}
	
    //getters and setters for the friend group id
    @Id
    @GenericGenerator(name = "friendgroupautoinc", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		      parameters = {
			  @Parameter(name = "sequence_name", value = "friendgroupautoinc"),
			  @Parameter(name = "optimizer", value = "hilo"),
			  @Parameter(name = "initial_value", value = "1"),
			  @Parameter(name = "increment_size", value = "1") }
    )
    @GeneratedValue(generator = "friendgroupautoinc")
    @Column(name = "friendgroupid")
    public Long getFriendGroupId() {
	return friendGroupId;
    }
    public void setFriendGroupId(Long friendGroupId) {
	this.friendGroupId = friendGroupId;
    }
	
    //getters and setters for the name of the friend group
    @Field
    @Column(name = "name", nullable = false)
    public String getName() {
	return name;
    }
    public void setName(String name) {
	this.name = name;
    }

    @OneToMany(mappedBy = "friendGroup", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Connection> getConnections() {
		return connections;
	}

	public void setConnections(Set<Connection> connections) {
		this.connections = connections;
	}

	public void addUser(User u){
		Connection c = new Connection();
		FriendGroupStatus status = new FriendGroupStatus();
		status.setStatusId((short)1);
		status.setStatusName("approved");
		c.setFriendGroup(this);
		c.setUser(u);
		c.setIsAdmin(false);
		c.setFriendGroupStatus(status);
	    this.connections.add(c);
	}
	
	@OneToMany( mappedBy = "friendGroup", fetch = FetchType.EAGER, cascade= CascadeType.ALL)
	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	
	public void sendMessage(Message m){
		this.messages.add(m);
	}
	
	public void removeUser(User u){
		this.connections.remove(u);
	}

	//to String method
    @Override
    public String toString() {
	return "FriendGroup [friendGroupId=" + friendGroupId + ", name=" + name + "]";
    }

	
}
