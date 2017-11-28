package com.whenufree.controllers;

import com.whenufree.services.SearchService;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
public class SearchController{
    private SearchService searchService;

    public SearchController(SearchService searchService){
	this.searchService = searchService;
    }

    
    public static class SearchQuery{
	public String term;
	public String type;
	public SearchQuery(){
	}
    }
    
    
     @RequestMapping(path="/search", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List> search(@RequestBody SearchQuery sq){	
	 return new ResponseEntity<>(searchService.search(sq.term, sq.type), HttpStatus.OK);
    }

}
