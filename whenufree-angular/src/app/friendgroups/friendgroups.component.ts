import { Component, OnInit } from '@angular/core';
import {GroupUserService} from '../services/groupuser.service';
import {UserService} from '../services/user.service';
import {FriendGroup} from '../domain/friendgroup';
import {User} from '../domain/user';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

export enum PageOption {
	  home = "HOME", 
	  select = "SELECT",
	  menu = "MENU"
  };

@Component({
  selector: 'app-friendgroups',
  template: `
  <app-navbar></app-navbar>
  <h2> Friend Groups Page</h2>
  <router-outlet></router-outlet>
  `,
  styleUrls: ['./friendgroups.component.css']
})
export class FriendGroupsComponent {
	/*
  friendgroups: Friendgroup[] = [];
  currentUser: User;
  page: PageOption = PageOption.select;
  groupName: string;
  
  constructor(private groupuserService: GroupUserService,
              private userService: UserService) { }

  ngOnInit() {
    this.userService.getUser().subscribe(
		user => this.currentUser = user
		error => this.router.navigate([''])
	);
    this.groupuserService.getFriendGroups().subscribe(
		friendgroups => this.friendgroups = friendgroups
	);
  }

  onPageChange( newPage: PageOption): void {
	  this.page = newPage;
  }

  onFriendSelect( groupName: string): void {
	  this.groupName = groupName;
  }
*/
}
