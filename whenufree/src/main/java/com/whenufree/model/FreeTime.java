package com.whenufree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "freetime")
public class FreeTime {

	//free time id
	private Long freeTimeId;
	//connections
	private User user;
	private TimeSlot timeSlot;
	//scheduled/default
	private Boolean scheduled;
	private Boolean isDefault;
	
	public FreeTime() {}

	//getters and setters
    @Id
    @GenericGenerator(name = "freetimeautoinc", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		      parameters = {
			  @Parameter(name = "sequence_name", value = "freetimeautoinc"),
			  @Parameter(name = "optimizer", value = "hilo"),
			  @Parameter(name = "initial_value", value = "1"),
			  @Parameter(name = "increment_size", value = "1") }
    )
    @GeneratedValue(generator = "freetimeautoinc")
	@Column(name = "freetimeid")
	public Long getFreeTimeId() {
		return freeTimeId;
	}

	public void setfreeTimeId(Long id) {
		this.freeTimeId = id;
	}

	@ManyToOne
	@JoinColumn(name="userid", referencedColumnName="userid", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name="timeslotid", referencedColumnName="timeslotid", nullable = false)
	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

       
    @Column(name = "scheduled", nullable = false)
	public Boolean getScheduled() {
		return scheduled;
	}

	public void setScheduled(Boolean scheduled) {
		this.scheduled = scheduled;
	}

	
    @Column(name = "isdefault", nullable = false)
	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	//to String method
	@Override
	public String toString() {
		return "FreeTime [freeTimeiId=" + freeTimeId + ", userId=" + user.getUserId() + ", timeSlotId=" + timeSlot.getTimeSlotId() + ", scheduled=" + scheduled
				+ ", isDefault=" + isDefault + "]";
	}
	
}
