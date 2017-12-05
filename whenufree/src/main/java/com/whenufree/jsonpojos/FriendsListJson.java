package com.whenufree.jsonpojos;

import com.whenufree.model.FriendsList;

public class FriendsListJson{
    private Long userId;
    private Long friendId;
    private String status;

    public FriendsListJson(){
    }

    public FriendsListJson(FriendsList fl){
	this.userId = fl.getFriendsListPK().getUserId();
	this.friendId = fl.getFriendsListPK().getFriendId();
	this.status = fl.getStatus().getStatusName();
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
     * Gets the value of friendId
     *
     * @return the value of friendId
     */
    public final Long getFriendId() {
	return this.friendId;
    }

    /**
     * Sets the value of friendId
     *
     * @param argFriendId Value to assign to this.friendId
     */
    public final void setFriendId(final Long argFriendId) {
	this.friendId = argFriendId;
    }

    /**
     * Gets the value of status
     *
     * @return the value of status
     */
    public final String getStatus() {
	return this.status;
    }

    /**
     * Sets the value of status
     *
     * @param argStatus Value to assign to this.status
     */
    public final void setStatus(final String argStatus) {
	this.status = argStatus;
    }

}
