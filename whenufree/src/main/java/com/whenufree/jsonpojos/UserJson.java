package com.whenufree.jsonpojos;

import java.util.Set;
import java.util.HashSet;

import com.whenufree.model.User;
import com.whenufree.model.FriendsList;
import com.whenufree.model.Connection;
import com.whenufree.jsonpojos.FriendsListJson;
import com.whenufree.jsonpojos.ConnectionJson;

public class UserJson{
    private Long userId;
    private String email;
    private String firstname;
    private String lastname;
    private String phone;
    private String password;
    private Set<FriendsListJson> friendsList;
    private Set<ConnectionJson> connections;
    
    public UserJson(){
    }

    public UserJson(User u){
	this.userId = u.getUserId();
	this.email = u.getEmail();
	this.firstname = u.getFirstname();
	this.lastname = u.getLastname();
	this.phone = u.getPhone();
	this.friendsList = new HashSet<>();
	for(FriendsList friend : u.getFriendsList()){
	    this.friendsList.add(new FriendsListJson(friend));
	}

	this.connections = new HashSet<>();
	for(Connection connection : u.getConnections()){
	    this.connections.add(new ConnectionJson(connection));
	}
    }
    
    /**
     * Gets the value of userId
     *
     * @return the value of userId
     */
    public final Long getUserId() {
	return this.userId;
    }

    /**
     * Sets the value of userId
     *
     * @param argUserId Value to assign to this.userId
     */
    public final void setUserId(final Long argUserId) {
	this.userId = argUserId;
    }

    /**
     * Gets the value of email
     *
     * @return the value of email
     */
    public final String getEmail() {
	return this.email;
    }

    /**
     * Sets the value of email
     *
     * @param argEmail Value to assign to this.email
     */
    public final void setEmail(final String argEmail) {
	this.email = argEmail;
    }

    /**
     * Gets the value of firstname
     *
     * @return the value of firstname
     */
    public final String getFirstname() {
	return this.firstname;
    }

    /**
     * Sets the value of firstname
     *
     * @param argFirstname Value to assign to this.firstname
     */
    public final void setFirstname(final String argFirstname) {
	this.firstname = argFirstname;
    }

    /**
     * Gets the value of lastname
     *
     * @return the value of lastname
     */
    public final String getLastname() {
	return this.lastname;
    }

    /**
     * Sets the value of lastname
     *
     * @param argLastname Value to assign to this.lastname
     */
    public final void setLastname(final String argLastname) {
	this.lastname = argLastname;
    }

    /**
     * Gets the value of phone
     *
     * @return the value of phone
     */
    public final String getPhone() {
	return this.phone;
    }

    /**
     * Sets the value of phone
     *
     * @param argPhone Value to assign to this.phone
     */
    public final void setPhone(final String argPhone) {
	this.phone = argPhone;
    }
    
    /**
     * Gets the value of password
     *
     * @return the value of password
     */
    public final String getPassword() {
	return this.password;
    }

    /**
     * Sets the value of password
     *
     * @param argPassword Value to assign to this.password
     */
    public final void setPassword(final String argPassword) {
	this.password = argPassword;
    }


	/**
	 * Gets the value of password
	 *
	 * @return the value of password
	 */
	public final String getPassword() {
		return this.password;
	}

	/**
	 * Sets the value of password
	 *
	 * @param argPassword Value to assign to this.password
	 */
	public final void setPassword( final String argPassword) {
		this.password = argPassword;
	}

    public Set<FriendsListJson> getFriendsList(){
	return this.friendsList;
    }

    public void setFriendsList(Set<FriendsListJson> fl){
	this.friendsList = fl;
    }

    public Set<ConnectionJson> getConnections(){
	return this.connections;
    }

    public void setConnections(Set<ConnectionJson> connections){
	this.connections = connections;
    }
	
     //To String Method
    @Override
    public String toString() {
	return "UserJson [userId=" + userId + ", email=" + email + ", firstname=" + firstname + ", lastname=" + lastname
	    + ", phone=" + phone + ", password=" + password + "]";
    }
    
}   
 
