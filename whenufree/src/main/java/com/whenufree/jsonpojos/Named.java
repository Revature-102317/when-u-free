package com.whenufree.jsonpojos;

import com.whenufree.model.User;
import com.whenufree.model.FriendGroup;

public class Named{
    private Long id;
    private String className;
    private String name;

    public Named(){
    }
    
    public Named(User user){
	this.className = "User";
	this.id = user.getUserId();
	this.name = user.getFirstname() + " " + user.getLastname();
    }
    public Named(FriendGroup fg){
	this.className = "FriendGroup";
	this.id = fg.getFriendGroupId();
	this.name = fg.getName();
    }
    
    /**
     * Gets the value of id
     *
     * @return the value of id
     */
    public final Long getId() {
	return this.id;
    }

    /**
     * Sets the value of id
     *
     * @param argId Value to assign to this.id
     */
    public final void setId(Long argId) {
	this.id = argId;
    }

    /**
     * Gets the value of className
     *
     * @return the value of className
     */
    public final String getClassName() {
	return this.className;
    }

    /**
     * Sets the value of className
     *
     * @param argClassName Value to assign to this.className
     */
    public final void setClassName(final String argClassName) {
	this.className = argClassName;
    }

    /**
     * Gets the value of name
     *
     * @return the value of name
     */
    public final String getName() {
	return this.name;
    }

    /**
     * Sets the value of name
     *
     * @param argName Value to assign to this.name
     */
    public final void setName(final String argName) {
	this.name = argName;
    }

}
