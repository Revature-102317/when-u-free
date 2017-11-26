package com.whenufree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="friendgroup")
public class FriendGroup {

    private Long friendGroupId;
    private String name;
	
    //no args constructor
    public FriendGroup() {}
	
    //getters and setters for the friend group id
    @Id
    @GenericGenerator(name = "friendgroupautoinc", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		      parameters = {
			  @Parameter(name = "sequence_name", value = "friendgroupautoinc"),
			  @Parameter(name = "optimizer", value = "hilo"),
			  @Parameter(name = "initial_value", value = "1"),
			  @Parameter(name = "increment_size", value = "1") }
    )
    @GeneratedValue(generator = "friendgroupautoinc")
    @Column(name = "friendgroupid")
    public Long getFriendGroupId() {
	return friendGroupId;
    }
    public void setFriendGroupId(Long friendGroupId) {
	this.friendGroupId = friendGroupId;
    }
	
    //getters and setters for the name of the friend group
    @Column(name = "name", nullable = false)
    public String getName() {
	return name;
    }
    public void setName(String name) {
	this.name = name;
    }

    //to String method
    @Override
    public String toString() {
	return "FriendGroup [friendGroupId=" + friendGroupId + ", name=" + name + "]";
    }

	
}
