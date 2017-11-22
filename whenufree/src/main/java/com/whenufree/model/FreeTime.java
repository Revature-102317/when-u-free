package com.whenufree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "freetime")
public class FreeTime {

	//free time id
	private Long freeTimeId;
	//connections
	private long userId;
	private long timeSlotId;
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

	@OneToMany
	@JoinColumn(name="userid", referencedColumnName="userid")
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@OneToMany
	@JoinColumn(name="timeslotid", referencedColumnName="timeslotid")
	public long getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeId(long timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	@Id
	@Column(name = "scheduled")
	public short getScheduled() {
		return scheduled;
	}

	public void setScheduled(short scheduled) {
		this.scheduled = scheduled;
	}

	@Id
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
		return "FreeTime [freeTimeiId=" + freeTimeId + ", userId=" + userId + ", timeSlotId=" + timeSlotId + ", scheduled=" + scheduled
				+ ", isDefault=" + isDefault + "]";
	}
	
}
