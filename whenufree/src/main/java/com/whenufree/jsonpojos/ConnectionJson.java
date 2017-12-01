package com.whenufree.jsonpojos;

import com.whenufree.model.Connection;

public class ConnectionJson{

    private Long userId;
    private Long friendGroupId;
    private Boolean isAdmin;
    private String status;
    
    public ConnectionJson(){
    }

    public ConnectionJson(Connection connection){
	this.userId = connection.getUser().getUserId();
	this.friendGroupId = connection.getFriendGroup().getFriendGroupId();
	this.isAdmin = connection.getIsAdmin();
	this.status = connection.getFriendGroupStatus().getStatusName();
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
     * Gets the value of friendGroupId
     *
     * @return the value of friendGroupId
     */
    public final Long getFriendGroupId() {
	return this.friendGroupId;
    }

    /**
     * Sets the value of friendGroupId
     *
     * @param argFriendGroupId Value to assign to this.friendGroupId
     */
    public final void setFriendGroupId(final Long argFriendGroupId) {
	this.friendGroupId = argFriendGroupId;
    }

    /**
     * Gets the value of isAdmin
     *
     * @return the value of isAdmin
     */
    public final Boolean getIsAdmin() {
	return this.isAdmin;
    }

    /**
     * Sets the value of isAdmin
     *
     * @param argIsAdmin Value to assign to this.isAdmin
     */
    public final void setIsAdmin(final Boolean argIsAdmin) {
	this.isAdmin = argIsAdmin;
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
