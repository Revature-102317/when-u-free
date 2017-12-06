package com.whenufree.jsonpojos;

import java.time.LocalDateTime;

import com.whenufree.model.Message;
import com.whenufree.model.Poll;

public class MessageJson {
	
    private Long messageId;
    private String text;
    private LocalDateTime timestamp;
    private Boolean pinned;
    private Poll poll;
    private String user;
    
    public MessageJson() {
    }
    
    public MessageJson(Message m){
    	this.messageId = m.getMessageId();
    	this.text = m.getText();
    	this.timestamp = m.getTimestamp();
    	this.pinned = m.getPinned();
    	this.poll = m.getPoll();
    	this.user = m.getAuthor().getFirstname()+" "+m.getAuthor().getLastname();
    }

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Boolean getPinned() {
		return pinned;
	}

	public void setPinned(Boolean pinned) {
		this.pinned = pinned;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}
    
   public User getUser() {
	   return user;
   }

   public void setUser(User user) {
	   this.user = user;
}
