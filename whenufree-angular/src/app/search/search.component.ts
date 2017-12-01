import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient, HttpParams} from '@angular/common/http';

import {SocialNetworkService} from '../services/social-network.service';
import {AuthenticationService} from '../services/authentication.service'
import {Named} from '../domain/named'
import {FriendsList} from '../domain/friendsList'
import {User} from '../domain/user'

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

    results: Named[];

    currentUser: User;
    
    searchStr: string;
    searchType: string = "all";

    constructor(private http: HttpClient,
		private authService: AuthenticationService,
		private snService: SocialNetworkService) { }
      

    ngOnInit() {
	this.getCurrentUser();
    }

    
    onSubmit(){
	var searchQuery = {'term': this.searchStr, 'type': this.searchType};
	console.log(JSON.stringify(searchQuery));

	let options = {withCredentials: true}
	this.http.post<Named[]>("http://localhost:8080/search", searchQuery, options).subscribe(data => {
	    this.results = data;
	});
    }

    getCurrentUser(){
	this.authService.getUser().subscribe(
	    data => {
		this.currentUser = data;
	    });
	console.log(JSON.stringify(this.currentUser));
    }
    
    displayAddFriend(n: Named){
	let ret = true;
	if(n.className !== 'User'){
	    ret = false;
	}else if(n.id === this.currentUser.userId){
	    ret = false
	}else{
	    for(let fl of this.currentUser.friendsList){
		if(n.id === fl.friendId){
		    ret = false
		}
	    }
	}
	return ret
    }

    displayJoinGroup(n: Named){
	let ret = true;
	if(n.className !== 'FriendGroup'){
	    ret = false;
	} else{
	    for(let conn of this.currentUser.connections){
		if(n.id === conn.friendGroupId){
		    ret = false;
		}
	    }
	}
	return ret;
    }

    onJoinGroup(n){
	this.snService.joinGroup(n).subscribe(
	    data => {
		this.getCurrentUser();
	    }
	);
    }

    onAddFriend(n){
	this.snService.addFriend(n).subscribe(
	    data => {
		this.getCurrentUser();
	    }
	);
    }

}
