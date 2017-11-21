package com.whenufree.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

import com.whenufree.model.FriendGroup;
import com.whenufree.model.Poll;

@Entity
@Table(name = "message")
public class Message{
    private Long messageId;
    private String text;
    private LocalDateTime timestamp;
    
    private FriendGroup friendGroup;
    private Poll poll;
    
    
    /**
     * Gets the value of pollId
     *
     * @return the value of pollId
     */
    @Id
    @Column(name = "messageid")
    public Long getMessageId() {
	return this.messageId;
    }

    /**
     * Sets the value of pollId
     *
     * @param argPollId Value to assign to this.pollId
     */
    public void setMessageId(final Long argMessageId) {
	this.messageId = argMessageId;
    }

    /**
     * Gets the value of description
     *
     * @return the value of description
     */
    @Column(name = "text")
    public String getText() {
	return this.text;
    }

    /**
     * Sets the value of description
     *
     * @param argDescription Value to assign to this.description
     */
    public void setText(final String argText) {
	this.text = argText;
    }

    
    /**
     * Gets the value of timestamp
     *
     * @return the value of timestamp
     */
    @Column(name = "timestamp")
    @CreationTimestamp
    public LocalDateTime getTimestamp() {
	return this.timestamp;
    }

    /**
     * Sets the value of timestamp
     *
     * @param argTimestamp Value to assign to this.timestamp
     */
    public void setTimestamp(LocalDateTime argTimestamp) {
	this.timestamp = argTimestamp;
    }


    
    /**
     * Gets the value of friendGroup
     *
     * @return the value of friendGroup
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friendgroupid", referencedColumnName = "friendgroupid", nullable = false)
    public FriendGroup getFriendGroup() {
	return this.friendGroup;
    }

    /**
     * Sets the value of friendGroup
     *
     * @param argFriendGroup Value to assign to this.friendGroup
     */
    public void setFriendGroup(FriendGroup argFriendGroup) {
	this.friendGroup = argFriendGroup;
    }

    
    /**
     * Gets the value of poll
     *
     * @return the value of poll
     */
    @OneToOne
    @JoinColumn(name = "pollid", referencedColumnName = "pollid", nullable = true)
    public final Poll getPoll() {
	return this.poll;
    }

    /**
     * Sets the value of poll
     *
     * @param argPoll Value to assign to this.poll
     */
    public void setPoll(Poll argPoll) {
	this.poll = argPoll;
    }


}
