package com.whenufree.model;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Field;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.whenufree.model.PollOption;
import com.whenufree.model.FriendsList;
import com.whenufree.model.Connection;
import com.whenufree.model.FriendGroup;
import com.whenufree.model.FriendGroupStatus;
import com.whenufree.model.FreeTime;

@Indexed
@Entity
@Table(name="usr", indexes = {@Index(columnList = "email", unique = true)})
public class User{
    private Long userId;
    private String email;
    private String firstname;
    private String lastname;
    private String phone;
    private String passwordHash;

    private Set<PollOption> votes;
    private Set<FriendsList> friendsList; 
    private Set<Connection> connections;
    private Set<FreeTime> freeTimes;
    
    //no args constructor
    public User() {
	votes = new HashSet<>();
	friendsList = new HashSet<>();
	connections = new HashSet<>();
    }
    
    /**
     * Gets the value of id
     *
     * @return the value of id
     */
    @Id
    @GenericGenerator(name = "userautoinc", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		      parameters = {
			  @Parameter(name = "sequence_name", value = "userautoinc"),
			  @Parameter(name = "optimizer", value = "hilo"),
			  @Parameter(name = "initial_value", value = "1"),
			  @Parameter(name = "increment_size", value = "1") }
    )
    @GeneratedValue(generator = "userautoinc")
    @Column(name="userid")
    public Long getUserId() {
	return this.userId;
    }

    /**
     * Sets the value of id
     *
     * @param argId Value to assign to this.id
     */
    public void setuserId(final Long argId) {
	this.userId = argId;
    }

    /**
     * Gets the value of email
     *
     * @return the value of email
     */
    @Field
    @NaturalId
    @Column(name="email", nullable = false)
    public String getEmail() {
	return this.email;
    }

    /**
     * Sets the value of email
     *
     * @param argEmail Value to assign to this.email
     */
    public void setEmail(final String argEmail) {
	this.email = argEmail;
    }

    /**
     * Gets the value of firstname
     *
     * @return the value of firstname
     */
    @Field
    @Column(name="firstname", nullable = false)
    public String getFirstname() {
	return this.firstname;
    }

    /**
     * Sets the value of firstname
     *
     * @param argFirstname Value to assign to this.firstname
     */
    public void setFirstname(final String argFirstname) {
	this.firstname = argFirstname;
    }

    /**
     * Gets the value of lastname
     *
     * @return the value of lastname
     */
    @Field
    @Column(name="lastname", nullable = false)
    public String getLastname() {
	return this.lastname;
    }

    /**
     * Sets the value of lastname
     *
     * @param argLastname Value to assign to this.lastname
     */
    public void setLastname(final String argLastname) {
	this.lastname = argLastname;
    }

    /**
     * Gets the value of phone
     *
     * @return the value of phone
     */
    @Column(name="phone", nullable = true)
    public String getPhone() {
	return this.phone;
    }

    /**
     * Sets the value of phone
     *
     * @param argPhone Value to assign to this.phone
     */
    public void setPhone(final String argPhone) {
	this.phone = argPhone;
    }

    /**
     * Gets the value of passwordHash
     *
     * @return the value of passwordHash
     */
    @Column(name="passwordhash", nullable = false)
    public String getPasswordHash() {
	return this.passwordHash;
    }

    /**
     * Sets the value of passwordHash
     *
     * @param argPasswordHash Value to assign to this.passwordHash
     */
    public void setPasswordHash(final String argPasswordHash) {
	this.passwordHash = argPasswordHash;
    }

    
    @ManyToMany(mappedBy = "voters", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<PollOption> getVotes(){
	return this.votes;
    }

    public void setVotes(Set<PollOption> votes){
	this.votes = votes;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<FriendsList> getFriendsList() {
	return this.friendsList;
    }

    public void setFriendsList(Set<FriendsList> friendsList) {
	this.friendsList = friendsList;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<FreeTime> getFreeTimes(){
	return this.freeTimes;
    }

    public void setFreeTimes(Set<FreeTime> argFreeTimes){
	this.freeTimes = argFreeTimes;
    }
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Connection> getConnections(){
	return this.connections;
    }

    public void setConnections(Set<Connection> connections){
	this.connections = connections;
    }
    
    public void joinGroup(FriendGroup group){
	Connection newConnection = new Connection();
	FriendGroupStatus status = new FriendGroupStatus();
	status.setStatusId((short)2);
	status.setStatusName("applied");
	newConnection.setUser(this);
	newConnection.setFriendGroup(group);
	newConnection.setIsAdmin(false);
	newConnection.setFriendGroupStatus(status);

	this.connections.add(newConnection);
	//group.getConnections().add(newConnection);
    }

    public void acceptGroup(FriendGroup group){
	FriendGroupStatus acceptedStatus = new FriendGroupStatus();
	acceptedStatus.setStatusId((short)1);
	acceptedStatus.setStatusName("approved");

	for(Connection c : this.connections){
	    if(c.getFriendGroup().getFriendGroupId()
	       .equals(group.getFriendGroupId()) &&
	       c.getFriendGroupStatus().getStatusName()
	       .equals("invited")){
		c.setFriendGroupStatus(acceptedStatus);
		break;    
	    }
	} 	
    }

    public void leaveGroup(FriendGroup group){
	Iterator<Connection> iter = this.connections.iterator();

	while(iter.hasNext()){
	    Connection current = iter.next();
	    if(current.getFriendGroup().getFriendGroupId()
	       .equals(group.getFriendGroupId())){
		iter.remove();
		break;
	    }
	}

	iter = group.getConnections().iterator();
	while(iter.hasNext()){
	    Connection current = iter.next();
	    if(current.getUser().getUserId()
	       .equals(this.userId)){
		iter.remove();
		break;
	    }
	}
    }

    public void addFriend(User friend){
	FriendsList newEntry = new FriendsList();

	FriendsListStatus status = new FriendsListStatus();
	status.setStatusId((short)2);
	status.setStatusName("pending");
	newEntry.setFriendsListPK(new FriendsList.FriendsListPK());
	newEntry.getFriendsListPK().setUserId(this.getUserId());
	newEntry.getFriendsListPK().setFriendId(friend.getUserId());
	newEntry.setUser(this);
	newEntry.setFriend(friend);
	newEntry.setStatus(status);
	this.friendsList.add(newEntry);

	FriendsList mirror = new FriendsList();
	FriendsListStatus mirrorStatus = new FriendsListStatus();
	mirrorStatus.setStatusId((short)3);
	mirrorStatus.setStatusName("reverse-pending");
	mirror.setFriendsListPK(new FriendsList.FriendsListPK());
	mirror.getFriendsListPK().setUserId(friend.getUserId());
	mirror.getFriendsListPK().setFriendId(this.getUserId());
	mirror.setUser(friend);
	mirror.setFriend(this);
	mirror.setStatus(mirrorStatus);
	friend.getFriendsList().add(mirror);
	
    }

    public void acceptFriend(User friend){
	FriendsListStatus acceptedStatus = new FriendsListStatus();
	acceptedStatus.setStatusId((short)1);
	acceptedStatus.setStatusName("approved");

	for(FriendsList l : this.friendsList){
	    if(l.getFriendsListPK().getFriendId().equals(friend.getUserId())
	       && l.getStatus().getStatusName().equals("reverse-pending")){
		l.setStatus(acceptedStatus);
		break;
	    }	
	}

	Set<FriendsList> mirrorList =  friend.getFriendsList(); 
	for(FriendsList l : mirrorList){
	    if(l.getFriendsListPK().getFriendId().equals(this.userId)){
		l.setStatus(acceptedStatus);
		break;
	    }	
	}
    }

    public void removeFriend(User friend){
	Iterator<FriendsList> iter = this.friendsList.iterator();

	while(iter.hasNext()){
	    FriendsList current = iter.next();
	    if(current.getFriendsListPK().getFriendId()
	       .equals(friend.getUserId())){
		iter.remove();
		break;
	    }
	}

	iter = friend.getFriendsList().iterator();
	while(iter.hasNext()){
	    FriendsList current = iter.next();
	    if(current.getFriendsListPK().getFriendId()
	       .equals(this.userId)){
		iter.remove();
		break;
	    }
	}
    }
    
    //To String Method
    @Override
    public String toString() {
	return "User [userId=" + userId + ", email=" + email + ", firstname=" + firstname + ", lastname=" + lastname
	    + ", phone=" + phone + ", passwordHash=" + passwordHash + "]";
    }
    
}
