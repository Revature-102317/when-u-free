import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';

import {SocialNetworkService} from '../services/social-network.service';

import {UserService} from '../services/user.service';
import {User} from '../domain/user';
import {Named} from '../domain/named';

@Component({
  selector: 'app-group-invite-user',
  templateUrl: './group-invite-user.component.html',
  styleUrls: ['./group-invite-user.component.css']
})
export class GroupInviteUserComponent implements OnInit {

    results: Named[];
    
    currentUser: User;
    
    searchStr: string;
    searchType: string = "all";
    
    constructor(private http: HttpClient,
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
	this.http.post<Named[]>("http://localhost:8080/searchnonmembers", searchQuery, options)
	    .subscribe(data => {
		this.results = data;
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
	    this.snService.inviteUser(n, id).subscribe(data => {});
	});
    }
    
}
