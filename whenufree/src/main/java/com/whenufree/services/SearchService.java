package com.whenufree.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.whenufree.search.UserGroupSearch;


@Service
public class SearchService{
    private UserGroupSearch ugs;

    @Autowired
    public SearchService(UserGroupSearch ugs){
	this.ugs = ugs;
    }

    public List search(String text, String type){
	return ugs.search(text, type);
    }
    
}
