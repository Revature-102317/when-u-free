package com.whenufree.model;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.EmbeddedId;
import javax.persistence.MapsId;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

import com.whenufree.model.User;

@Entity
@Table(name = "polloption")
public class PollOption{
 
    private PollOptionPk pk;
    private Poll poll;
    private Set<User> voters;
    
    public static class PollOptionPk implements Serializable{
	private Long pollId;
	private String description;

	private PollOptionPk(){
	}

	private PollOptionPk(Long pollId, String description){
	    this.pollId = pollId;
	    this.description = description;
	}

	@Column(name = "pollid")
	public Long getPollId(){
	    return this.pollId;
	}

	public void setPollId(Long pollId){
	    this.pollId = pollId;
	}

	@Column(name = "description")
	public String getDescription(){
	    return this.description;
	}

	public void setDescription(String description){
	    this.description = description;
	}

	@Override
	public boolean equals(Object o){
	    boolean ret = true;
	    if (o == null || getClass() != o.getClass()){
		ret = false;
	    }
	    else{
		PollOptionPk that = (PollOptionPk) o;
		ret = this.pollId.equals(that.getPollId()) &&
		    this.description.equals(that.getDescription());
	    }
	    return ret;
	}
	@Override
	public int hashCode(){
	    return Objects.hash(this.pollId, this.description);
	}
    }

    public PollOption(){
	this.voters = new HashSet<>();
    }
    
    /**
     * Gets the value of pk
     *
     * @return the value of pk
     */
    @EmbeddedId
    public PollOptionPk getPk() {
	return this.pk;
    }

    /**
     * Sets the value of pk
     *
     * @param argPk Value to assign to this.pk
     */
    public void setPk(final PollOptionPk argPk) {
	this.pk = argPk;
    }

    /**
     * Gets the value of poll
     *
     * @return the value of poll
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pollId")
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
    @JoinTable(name = "votes", joinColumns = {
	    @JoinColumn(name = "pollid", nullable = false, updatable = false, referencedColumnName = "poll_pollid"),
	    @JoinColumn(name = "poll_option_descrption", nullable = false, updatable = false, referencedColumnName = "description")},
	    inverseJoinColumns = { @JoinColumn(name = "userid",
					       nullable = false, updatable = false)})
    public Set<User> getVoters(){
	return this.voters;
    }

    public void setVoters(Set<User> voters){
	this.voters = voters;
    }

}
