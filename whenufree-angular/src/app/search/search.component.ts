import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient, HttpParams} from '@angular/common/http';

import {Named} from '../domain/named'

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

    results: Named[];

    searchStr: string;
    searchType: string = "all";

    constructor(private http: HttpClient) { }
      

    ngOnInit() {
    }

    
    onSubmit(){
	var searchQuery = {'term': this.searchStr, 'type': this.searchType};
	console.log(JSON.stringify(searchQuery));

	let options = {withCredentials: true}
	this.http.post<Named[]>("http://localhost:8085/search", searchQuery, options).subscribe(data => {
	    this.results = data;
	    console.log(JSON.stringify(data));
	});
    }
  
}
