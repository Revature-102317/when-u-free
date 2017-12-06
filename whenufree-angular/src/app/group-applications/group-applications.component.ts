import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {SocialNetworkService} from '../services/social-network.service';

import {Named} from '../domain/named';
@Component({
  selector: 'app-group-applications',
  templateUrl: './group-applications.component.html',
  styleUrls: ['./group-applications.component.css']
})
export class GroupApplicationsComponent implements OnInit {

    applications: Named[] = [];
    constructor(private snService: SocialNetworkService,
		private route: ActivatedRoute) { }

    ngOnInit() {
	this.getApplied();
    }

    getApplied(){
	this.route.params.subscribe(params =>{
	    let id = +params['id'];
	    this.snService.getApplied(id).subscribe(data => this.applications = data);
	});
    }

    onApprove(n: Named){
	this.route.params.subscribe(params =>{
	    let id = +params['id'];
	    this.snService.approveUser(n, id).subscribe(data => {});
	});
    }
}
