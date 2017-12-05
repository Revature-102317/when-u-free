package com.whenufree.model;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.EmbeddedId;
import javax.persistence.MapsId;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.whenufree.model.User;

@Entity
@Table(name = "polloption")
public class PollOption{

    private Long pollOptionId;
    private String description;
    private Poll poll;
    private Set<User> voters;
    

    public PollOption(){
	this.voters = new HashSet<>();
    }

    
    /**
     * Gets the value of pollOptionId
     *
     * @return the value of pollOptionId
     */
    @Id
    @GenericGenerator(name = "polloptionautoinc", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		      parameters = {
			  @Parameter(name = "sequence_name", value = "polloptionautoinc"),
			  @Parameter(name = "optimizer", value = "hilo"),
			  @Parameter(name = "initial_value", value = "1"),
			  @Parameter(name = "increment_size", value = "1") }
    )
    @GeneratedValue(generator = "polloptionautoinc")
    @Column(name = "polloptionid")
    public Long getPollOptionId() {
	return this.pollOptionId;
    }

    /**
     * Sets the value of pollOptionId
     *
     * @param argPollOptionId Value to assign to this.pollOptionId
     */
    public void setPollOptionId(Long argPollOptionId) {
	this.pollOptionId = argPollOptionId;
    }

    /**
     * Gets the value of description
     *
     * @return the value of description
     */
    @Column(name = "description", nullable = false)
    public String getDescription() {
	return this.description;
    }

    /**
     * Sets the value of description
     *
     * @param argDescription Value to assign to this.description
     */
    public void setDescription(String argDescription) {
	this.description = argDescription;
    }

    
    /**
     * Gets the value of poll
     *
     * @return the value of poll
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pollid", referencedColumnName = "pollid", nullable = false)
    public Poll getPoll() {
	return this.poll;
    }

    /**
     * Sets the value of poll
     *
     * @param argPoll Value to assign to this.poll
     */
    public void setPoll(final Poll argPoll) {
	this.poll = argPoll;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "votes", joinColumns = { @JoinColumn(name = "polloptionid", nullable = false, updatable = false, referencedColumnName = "polloptionid")}, inverseJoinColumns = { @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false, updatable = false)})
    public Set<User> getVoters(){
	return this.voters;
    }

    public void setVoters(Set<User> voters){
	this.voters = voters;
    }

}
