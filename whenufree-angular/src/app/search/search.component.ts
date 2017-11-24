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

    onSubmit(){
	var searchQuery = {'searchStr': this.searchStr, 'searchType': this.searchType};
	console.log(JSON.stringify(searchQuery));
    }
    
    constructor() { }

    ngOnInit() {
    }

}