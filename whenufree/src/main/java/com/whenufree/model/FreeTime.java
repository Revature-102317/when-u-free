package com.whenufree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "freetime")
public class FreeTime {

	//free time id
	private Long freeTimeId;
	//connections
	private User user;
	private TimeSlot timeSlot;
	//scheduled/default
	private Short scheduled;
	private Short isDefault;
	
	public FreeTime() {}

	//getters and setters
	@Id
	@Column(name = "freetimeid")
	public Long getFreeTimeId() {
		return freeTimeId;
	}

	public void setfreeTimeId(Long id) {
		this.freeTimeId = id;
	}

	@ManyToOne
	@JoinColumn(name="userid", referencedColumnName="userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name="timeslotid", referencedColumnName="timeslotid")
	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

       
	@Column(name = "scheduled")
	public short getScheduled() {
		return scheduled;
	}

	public void setScheduled(short scheduled) {
		this.scheduled = scheduled;
	}

	
	@Column(name = "isdefault")
	public short getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(short isDefault) {
		this.isDefault = isDefault;
	}

	//to String method
	@Override
	public String toString() {
		return "FreeTime [freeTimeiId=" + freeTimeId + ", userId=" + user.getUserId() + ", timeSlotId=" + timeSlot.getTimeSlotId() + ", scheduled=" + scheduled
				+ ", isDefault=" + isDefault + "]";
	}
	
}
