package com.whenufree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friendgroupstatus")
public class FriendGroupStatus {

	private Short statusId;
	private String statusName;
	
	@Id
	@Column(name = "statusid")
	public Short getStatusId() {
		return statusId;
	}
	public void setStatusId(Short statusId) {
		this.statusId = statusId;
	}
	
    @Column(name = "statusname", nullable = false)
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
	
}
