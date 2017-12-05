import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient, HttpParams} from '@angular/common/http';

import {SocialNetworkService} from '../services/social-network.service';
<<<<<<< HEAD
import {AuthenticationService} from '../services/authentication.service';
import { UserService } from '../services/user.service';
import {Named} from '../domain/named';
import {User} from '../domain/user';
import {FriendsList} from '../domain/friendsList';
=======
import {UserService} from '../services/user.service'
import {Named} from '../domain/named'
import {FriendsList} from '../domain/friendsList'
import {User} from '../domain/user'
>>>>>>> e74e72db7018938c39647dd0c6daf725096cec8d


@Component({
	selector: 'app-search',
	templateUrl: './search.component.html',
	styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

<<<<<<< HEAD
	results: Named[];

	currentUser: User;

	searchStr: string;
	searchType: string = "all";

	constructor(private http: HttpClient,
				private userService: UserService,
				private snService: SocialNetworkService) { }
	
	ngOnInit() {
		this.getCurrentUser();
	}

	onSubmit() {
		var searchQuery = {'term': this.searchStr, 'type': this.searchType};
		console.log(JSON.stringify(searchQuery));
		let options = {withCredentials: true};
		this.http.post<Named[]>("http://localhost:8080/search", searchQuery, options)
		.subscribe(data => {
			this.results = data;
		});
	}

	getCurrentUser(){
		this.userService.getUser().subscribe(
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
=======
    results: Named[];

    currentUser: User;

    searchStr: string;
    searchType: string = 'all';

    constructor(private http: HttpClient,
    private userService: UserService,
    private snService: SocialNetworkService) { }


    ngOnInit() {
      this.getCurrentUser();
    }


    onSubmit() {
	var type = 'all';
	if(this.searchType === 'groups'){
	    type = 'group';
	}else if(this.searchType === 'people'){
	    type = 'user';
	}
	var searchQuery = {'term': this.searchStr, 'type': type};
	console.log(JSON.stringify(searchQuery));
	
	let options = {withCredentials: true}
	this.http.post<Named[]>("http://localhost:8080/search", searchQuery, options).subscribe(data => {
	    this.results = data;
	});
    }


    getCurrentUser(){
	this.userService.getUser().subscribe(
	    data => {
		this.currentUser = data;
	    });
	    
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
>>>>>>> e74e72db7018938c39647dd0c6daf725096cec8d
		}
		return ret
	}
<<<<<<< HEAD
=======
	return ret;
    }
>>>>>>> e74e72db7018938c39647dd0c6daf725096cec8d

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
