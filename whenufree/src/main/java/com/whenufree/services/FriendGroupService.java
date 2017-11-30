package com.whenufree.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whenufree.dao.FriendGroupDao;
import com.whenufree.dao.UserDao;

@Service
public class FriendGroupService {
	
	private UserDao userDao;
	
	private FriendGroupDao friendGroupDao;
	
	@Autowired
	public FriendGroupService(UserDao userDao, FriendGroupDao friendGroupDao){
		
	}

}
