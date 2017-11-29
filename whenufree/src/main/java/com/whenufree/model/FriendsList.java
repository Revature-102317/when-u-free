package com.whenufree.model;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MapsId;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

import java.util.Objects;
import java.io.Serializable;

import com.whenufree.model.User;
import com.whenufree.model.FriendsListStatus;

@Entity
@Table(name = "friendslist")
public class FriendsList{

    
    private FriendsListPK friendsListPK;
    
    private User user;
    private User friend;
    private FriendsListStatus status;
    
    @Embeddable
    public static class FriendsListPK implements Serializable{

	private Long userId;
	private Long friendId;

	private FriendsListPK(){
	}

	private FriendsListPK(Long userId, Long friendId){
	    this.userId = userId;
	    this.friendId = friendId;
	}
	
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	public Long getUserId(){
	    return this.userId;
	}

	public void setUserId(Long userId){
	    this.userId = userId;
	}
	
	@JoinColumn(name = "friendid", referencedColumnName= "friendid")
	public Long getFriendId(){
	    return this.friendId;
	}

	public void setFriendId(Long friendId){
	    this.friendId = friendId;
	}

	@Override
	public boolean equals(Object o){
	    boolean ret = true;
	    if (o == null || this.getClass() != o.getClass()){ 
		ret = false;
	    } else{
		FriendsListPK that = (FriendsListPK) o;
	        ret = this.userId.equals(that.getUserId()) &&
		    this.friendId.equals(that.getFriendId());
	    }
	    return ret;
	}

	@Override
	public int hashCode(){
	    return Objects.hash(this.userId, this.friendId);
	}
	
    }
    
    /**
     * Gets the value of friendsListPK
     *
     * @return the value of friendsListPK
     */
    @EmbeddedId
    public FriendsListPK getFriendsListPK() {
	return this.friendsListPK;
    }

    /**
     * Sets the value of friendsListPK
     *
     * @param argFriendsListPK Value to assign to this.friendsListPK
     */
    public void setFriendsListPK(FriendsListPK argFriendsListPK) {
	this.friendsListPK = argFriendsListPK;
    }

    /**
     * Gets the value of status
     *
     * @return the value of status
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusid", referencedColumnName = "statusid", nullable = false)
    public FriendsListStatus getStatus() {
	return this.status;
    }

    /**
     * Sets the value of status
     *
     * @param argStatus Value to assign to this.status
     */
    public void setStatus(FriendsListStatus argStatus) {
	this.status = argStatus;
    }

    
    /**
     * Gets the value of user
     *
     * @return the value of user
     */
    @ManyToOne
    @MapsId("userId")
    public User getUser() {
	return this.user;
    }

    /**
     * Sets the value of user
     *
     * @param argUser Value to assign to this.user
     */
    public void setUser(User argUser) {
	this.user = argUser;
    }

    /**
     * Gets the value of friend
     *
     * @return the value of friend
     */
    @ManyToOne
    @MapsId("friendId")
    public User getFriend() {
	return this.friend;
    }

    /**
     * Sets the value of friend
     *
     * @param argFriend Value to assign to this.friend
     */
    public void setFriend(User argFriend) {
	this.friend = argFriend;
    }

}
