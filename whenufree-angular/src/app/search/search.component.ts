import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient, HttpParams} from '@angular/common/http';

import {AuthenticationService} from '../services/authentication.service'
import {Named} from '../domain/named'
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
		private authService: AuthenticationService) { }
      

    ngOnInit() {
	this.authService.getUser().subscribe(
	    data => {
		this.currentUser = data;
	    });
	console.log(JSON.stringify(this.currentUser));
    }

    
    onSubmit(){
	var searchQuery = {'term': this.searchStr, 'type': this.searchType};
	console.log(JSON.stringify(searchQuery));

	let options = {withCredentials: true}
	this.http.post<Named[]>("http://localhost:8085/search", searchQuery, options).subscribe(data => {
	    this.results = data;
	});
    }

    onAddFriend(id){
    }

    onJoinGroup(id){
    }
  
}
