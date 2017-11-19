package main.java.com.whenufree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timeslot")
public class TimeSlot {
	
	private long timeSlotId;
	private String dateTime;
	
	//no args constructor
	public TimeSlot () {}

	
	//Getters and setters for the time slot ID
	@Id
	@Column(name = "timeslotid")
	public long getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(long timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	//Getters and setters for the date time
	@Column(name = "datetime")
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	//To String method
	@Override
	public String toString() {
		return "TimeSlot [timeSlotId=" + timeSlotId + ", dateTime=" + dateTime + "]";
	}
	
}
