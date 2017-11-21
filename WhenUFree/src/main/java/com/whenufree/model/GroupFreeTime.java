package com.whenufree.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

/**
  * Created by Desmond George 11/20/2017
  */
@Entity
@Table(name = "groupfreetime")
public class GroupFreeTime {

    @Id
    @Column(name= "groupfreetimeid")
    private long groupFreeTimeId;
     /**
     * Gets the GroupFreeTime Id.
     *
     * @return the value of groupFreeTimeId
     */
    public long getGroupFreeTimeId() {
        return groupFreeTimeId;
    }

    /**
     * Sets the GroupFreeTime Id.
     *
     * @param groupFreeTimeId The new groupFreeTimeId value
     */
    public void setGroupFreeTimeId(long groupFreeTimeId) { groupFreeTimeId = groupFreeTimeId;
    }

    @Column(name= "numusers")
    private int numUsers;
    /**
     * Gets the number of users in the friend group.
     *
     * @return the value of numUsers
     */
    public int getNumUsers() {
        return numUsers;
    }
    /**
     * Sets the number of users in the friend group.
     *
     * @param numUsers the new value of the number of users in the friend group
     */
    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }

    // Associative table so we add the primary keys of
    @OneToMany
    @JoinColumn(name= "timeslotid", referencedColumnName= "timeslotid")
    private long timeSlotId;
    /**
     * Get the timeSlotId. This Colum contains a foreign key associated with the TimeSlot table.
     * This is an associative entity.
     *
     * @return the value of timeSlotId
     */
    public long getTimeslotId() {
        return timeSlotId;
    }
    /**
     * Adds a new time slot to the list of available slots to use when determining potential free time.
     *
     * @param timeSlotId the new time slot value we are adding to the group free time.
     */
    public void setTimeslotId(long timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    @OneToMany
    @JoinColumn(name= "friendgroupid", referencedColumnName= "friendgroupid")
    private long friendGroupId;
    /**
     * Get the friendGroupId. This Colum contains a foregin key associated with the FriendGroup table.
     * This is an associative entity.
     *
     * @return the value of friendGroupId
     */
    public long getFriendGroupId() {
        return friendGroupId;
    }
    /**
     * Set the value of the friend group Id that we are using.
     *
     * @param friendGroupId the value of the new friendGroupId
     */
    public void setFriendGroupId(long friendGroupId) {
        this.friendGroupId = friendGroupId;
    }

    public GroupFreeTime() { }

    public GroupFreeTime(long groupFreeTimeId, int numUsers) {
        this.groupFreeTimeId = groupFreeTimeId;
        this.numUsers = numUsers;
    }

    @Override
    public String toString() {
        return "groupFreeTime { " +
                "groupFreeTimeId: " + groupFreeTimeId +
                "numUsers: " + numUsers +
                "timeSlotId: " + timeSlotId +
                "friendGroupId: " + friendGroupId +
                '}';
    }
}