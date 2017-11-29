package com.whenufree.services;

import java.util.List;

import org.hibernate.Hibernate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
    
import com.whenufree.dao.UserDao;
import com.whenufree.model.User;
import com.whenufree.model.FriendsList;

@Service
public class UserService implements UserDetailsService{
    private UserDao dao;

    @Autowired
    public UserService(UserDao dao){
	this.dao = dao;
    }

    public List<User> findAll(){
	return dao.findAll();
    }

    public User findByEmail(String email){
	return dao.findByEmail(email);
    }

    @Transactional
    public User findByEmailAndInitializeFriendsList(String email){
	User u = dao.findByEmail(email);
	Hibernate.initialize(u.getFriendsList());
	System.out.println(1);
	System.out.println(u.getFriendsList().getClass());
	
	for(FriendsList f : u.getFriendsList())
	    System.out.println(f);
	return u;
    }

    public User save(User u){
	return dao.save(u);
    }

    public UserDetails loadUserByUsername(String username)
	throws UsernameNotFoundException{
	User user = dao.findByEmail(username);	
	if(user == null)
	    throw new UsernameNotFoundException("Username not found");
	UserBuilder builder =
	    org.springframework.security.core.userdetails.User.withUsername(user.getEmail());
	return builder
	    .password(user.getPasswordHash())
	    .authorities("ROLE_USER")
	    .build();
    }
}
