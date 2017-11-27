package com.whenufree.jsonpojos;

import com.whenufree.model.User;

public class UserJson{
    private String email;
    private String firstname;
    private String lastname;
    private String phone;

    public UserJson(){
    }

    public UserJson(User u){
	this.email = u.getEmail();
	this.firstname = u.getFirstname();
	this.lastname = u.getLastname();
	this.phone = u.getPhone();
    }
    
    /**
     * Gets the value of email
     *
     * @return the value of email
     */
    public final String getEmail() {
	return this.email;
    }

    /**
     * Sets the value of email
     *
     * @param argEmail Value to assign to this.email
     */
    public final void setEmail(final String argEmail) {
	this.email = argEmail;
    }

    /**
     * Gets the value of firstname
     *
     * @return the value of firstname
     */
    public final String getFirstname() {
	return this.firstname;
    }

    /**
     * Sets the value of firstname
     *
     * @param argFirstname Value to assign to this.firstname
     */
    public final void setFirstname(final String argFirstname) {
	this.firstname = argFirstname;
    }

    /**
     * Gets the value of lastname
     *
     * @return the value of lastname
     */
    public final String getLastname() {
	return this.lastname;
    }

    /**
     * Sets the value of lastname
     *
     * @param argLastname Value to assign to this.lastname
     */
    public final void setLastname(final String argLastname) {
	this.lastname = argLastname;
    }

    /**
     * Gets the value of phone
     *
     * @return the value of phone
     */
    public final String getPhone() {
	return this.phone;
    }

    /**
     * Sets the value of phone
     *
     * @param argPhone Value to assign to this.phone
     */
    public final void setPhone(final String argPhone) {
	this.phone = argPhone;
    }

}
