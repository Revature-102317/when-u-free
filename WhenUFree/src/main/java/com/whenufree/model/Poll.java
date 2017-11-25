package com.whenufree.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

import com.whenufree.model.FriendGroup;

@Entity
@Table(name = "poll")
public class Poll{
    private Long pollId;
    private String description;

    private FriendGroup friendGroup;

    
    
    /**
     * Gets the value of pollId
     *
     * @return the value of pollId
     */
    @Id
    @Column(name = "pollid")
    public Long getPollId() {
	return this.pollId;
    }

    /**
     * Sets the value of pollId
     *
     * @param argPollId Value to assign to this.pollId
     */
    public void setPollId(final Long argPollId) {
	this.pollId = argPollId;
    }

    /**
     * Gets the value of description
     *
     * @return the value of description
     */
    @Column(name = "description")
    public String getDescription() {
	return this.description;
    }

    /**
     * Sets the value of description
     *
     * @param argDescription Value to assign to this.description
     */
    public void setDescription(final String argDescription) {
	this.description = argDescription;
    }

    
    /**
     * Gets the value of friendGroup
     *
     * @return the value of friendGroup
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friendgroupid")
    public FriendGroup getFriendGroup() {
	return this.friendGroup;
    }

    /**
     * Sets the value of friendGroup
     *
     * @param argFriendGroup Value to assign to this.friendGroup
     */
    public void setFriendGroup(final FriendGroup argFriendGroup) {
	this.friendGroup = argFriendGroup;
    }

}
