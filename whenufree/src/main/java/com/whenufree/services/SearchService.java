package com.whenufree.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.whenufree.search.UserGroupSearch;

import com.whenufree.jsonpojos.Named;
import com.whenufree.model.User;
import com.whenufree.model.FriendGroup;

@Service
public class SearchService{
    private UserGroupSearch ugs;

    @Autowired
    public SearchService(UserGroupSearch ugs){
	this.ugs = ugs;
    }

    public List<Named> search(String text, String type){
	List l = ugs.search(text, type);
	List<Named> ret = new ArrayList<>();
	for(Object o: l){
	    if(o instanceof User)
		ret.add(new Named((User)o));
	    else if(o instanceof FriendGroup)
		ret.add(new Named((FriendGroup) o));
	}
	return ret;
    }
    
}
