import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';

import {SocialNetworkService} from '../services/social-network.service';

import {UserService} from '../services/user.service';
import {User} from '../domain/user';
import {Named} from '../domain/named';
import {UrlService} from '../services/url.service';

@Component({
  selector: 'app-group-invite-user',
  templateUrl: './group-invite-user.component.html',
  styleUrls: ['./group-invite-user.component.css']
})
export class GroupInviteUserComponent implements OnInit {

    results: Named[] = [];
    
    currentUser: User;
    
    searchStr: string;
    searchType: string = "all";
    
    constructor(private url: UrlService,
		private http: HttpClient,
		private userService: UserService,
		private snService: SocialNetworkService,
		private route: ActivatedRoute) { }

    ngOnInit() {
	this.getCurrentUser();
    }

    onSubmit() {
	var searchQuery = {'term': this.searchStr, 'type': 'user'};
	console.log(JSON.stringify(searchQuery));
	let options = {withCredentials: true};
	this.route.params.subscribe(params =>{
	    let id = +params['id'];
	    this.http.post<Named[]>(this.url.getUrl() + "/searchnonmembers/" + id, searchQuery, options)
		.subscribe(data => {
		    this.results = [];
		    if(this.searchType === 'friends'){
			for(let n of data){
			    for(let f of this.currentUser.friendsList){
				if(f.friendId === n.id){
				    this.results.push(n);
				}
			    }
			}
		    } else{
			this.results = data;
		    }
	    });
	});
    }

    getCurrentUser(){
	this.userService.getUser().subscribe(
	    data => {
		this.currentUser = data;
	    });
    }

    onInvite(n: Named){
	this.route.params.subscribe(params =>{
	    let id = +params['id'];
	    this.snService.inviteUser(n, id).subscribe(data => {
		this.onSubmit();
	    });
	    
	});
    }

    onClear(){
	this.results = [];
    }
    
}
