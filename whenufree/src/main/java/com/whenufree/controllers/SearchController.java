package com.whenufree.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.whenufree.model.FriendGroup;
import com.whenufree.model.Connection;
import com.whenufree.services.SearchService;
import com.whenufree.services.FriendGroupService;
import com.whenufree.jsonpojos.Named;

import java.util.Iterator;


@Controller
public class SearchController{

    private SearchService searchService;
    private FriendGroupService fgService;
    
    @Autowired
    public SearchController(SearchService searchService,
			    FriendGroupService fgService){
	this.searchService = searchService;
	this.fgService = fgService;
    }

    
    public static class SearchQuery{
	public String term;
	public String type;
	public SearchQuery(){
	}
    }
    
    
    @RequestMapping(path="/search", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Named>> search(@RequestBody SearchQuery sq){	
	 return new ResponseEntity<>(searchService.search(sq.term, sq.type), HttpStatus.OK);
    }

    @RequestMapping(path="/searchnonmembers/{id}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Named>> searchNonMemebers(@PathVariable Long id,
							@RequestBody SearchQuery sq){
	FriendGroup fg = fgService.findById(id);
	
	List<Named> ret = searchService.search(sq.term, sq.type);
	Iterator iter = ret.iterator();
	while(iter.hasNext()){
	    Named n = (Named)iter.next();
	    for(Connection c : fg.getConnections()){
		if(n.getId() == c.getUser().getUserId()){
		    iter.remove();
		}	    
	    }
	    
	}
	return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @RequestMapping(path="/searchmembers/{id}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Named>> searchMemebers(@PathVariable Long id,
							@RequestBody SearchQuery sq){
	FriendGroup fg = fgService.findById(id);
	
	List<Named> ret = searchService.search(sq.term, sq.type);
	Iterator iter = ret.iterator();
	while(iter.hasNext()){
	    Named n = (Named)iter.next();
	    boolean isMember = false;
	    for(Connection c : fg.getConnections()){
		if(n.getId() == c.getUser().getUserId()){
		    isMember = true;
		}	    
	    }
	    if(!isMember)
		iter.remove();
	}
	return new ResponseEntity<>(ret, HttpStatus.OK);
    }

}
