package com.whenufree.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.whenufree.model.FriendGroup;
import com.whenufree.model.Poll;

@Entity
@Table(name = "message",  indexes = {@Index(columnList = "friendgroupid")})
public class Message{
    private Long messageId;
    private String text;
    private LocalDateTime timestamp;
    private Boolean pinned;
    
    private FriendGroup friendGroup;
    private User author;
    private Poll poll;
    
    
    /**
     * Gets the value of pollId
     *
     * @return the value of pollId
     */
    @Id
    @GenericGenerator(name = "messageautoinc", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		      parameters = {
			  @Parameter(name = "sequence_name", value = "messageautoinc"),
			  @Parameter(name = "optimizer", value = "hilo"),
			  @Parameter(name = "initial_value", value = "1"),
			  @Parameter(name = "increment_size", value = "1") }
    )
    @GeneratedValue(generator = "messageautoinc")
    @Column(name = "messageid")
    public Long getMessageId() {
	return this.messageId;
    }

    /**
     * Sets the value of pollId
     *
     * @param argPollId Value to assign to this.pollId
     */
    public void setMessageId(Long argMessageId) {
	this.messageId = argMessageId;
    }

    /**
     * Gets the value of description
     *
     * @return the value of description
     */
    @Column(name = "text", nullable = false)
    public String getText() {
	return this.text;
    }

    /**
     * Sets the value of description
     *
     * @param argDescription Value to assign to this.description
     */
    public void setText(String argText) {
	this.text = argText;
    }

    
    /**
     * Gets the value of timestamp
     *
     * @return the value of timestamp
     */
    @Column(name = "tstamp", nullable = false)
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
     * Gets the value of pinned
     *
     * @return the value of pinned
     */
    @Column(name = "pinned", nullable = false)
    public Boolean getPinned() {
	return this.pinned;
    }

    /**
     * Sets the value of pinned
     *
     * @param argPinned Value to assign to this.pinned
     */
    public void setPinned(Boolean argPinned) {
	this.pinned = argPinned;
    }

    
    /**
     * Gets the value of friendGroup
     *
     * @return the value of friendGroup
     */
    @ManyToOne(fetch = FetchType.EAGER)
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
     * Gets the value of author
     *
     * @return the value of author
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorid", referencedColumnName = "userid", nullable = false)
    public User getAuthor() {
	return this.author;
    }

    /**
     * Sets the value of author
     *
     * @param argAuthor Value to assign to this.author
     */
    public void setAuthor(User argAuthor) {
	this.author = argAuthor;
    }

    
    /**
     * Gets the value of poll
     *
     * @return the value of poll
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pollid", referencedColumnName = "pollid", nullable = true)
    public Poll getPoll() {
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
