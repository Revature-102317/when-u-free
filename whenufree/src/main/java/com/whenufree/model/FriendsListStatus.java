package com.whenufree.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name = "friendsliststatus")
public class FriendsListStatus{
    private Long statusId;
    private String statusName;

    
    /**
     * Gets the value of statusId
     *
     * @return the value of statusId
     */
    @Id
    @Column(name = "statusid")
    public Long getStatusId() {
	return this.statusId;
    }

    /**
     * Sets the value of statusId
     *
     * @param argStatusId Value to assign to this.statusId
     */
    public void setStatusId(Long argStatusId) {
	this.statusId = argStatusId;
    }

    /**
     * Gets the value of statusName
     *
     * @return the value of statusName
     */
    @Column(name = "statusname")
    public String getStatusName() {
	return this.statusName;
    }

    /**
     * Sets the value of statusName
     *
     * @param argStatusName Value to assign to this.statusName
     */
    public void setStatusName(String argStatusName) {
	this.statusName = argStatusName;
    }

}
