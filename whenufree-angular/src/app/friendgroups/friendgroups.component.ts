import { Component, OnInit } from '@angular/core';
import {GroupuserService} from '../services/groupuser.service';
import {UserService} from '../services/user.service';
import {Router} from '@angular/router';
import {Friendgroup} from '../domain/friendgroup';
import {User} from '../domain/user';

// This component is the list of friend groups of the current user
// Clicking on one will redirect to the component known as friendgroup (without the s)

@Component({
  selector: 'app-friendgroups',
  templateUrl: './friendgroups.component.html',
  styleUrls: ['./friendgroups.component.css']
})
export class FriendgroupsComponent implements OnInit {
  friendgroups: Friendgroup[] = [];

  currentUser: User;

  constructor(private groupuserService: GroupuserService,
              private userService: UserService,
              private router: Router) { }

  ngOnInit() {
    this.userService.getUser().subscribe(
      user => this.currentUser = user,
      error => this.router.navigate([''])
    );
    this.getFriendGroups();
  }

  getFriendGroups() {
    this.groupuserService.getFriendGroups().subscribe(friendgroups => this.friendgroups = friendgroups);
  }

  postFriendGroup(friendgroupid: number) {
    this.groupuserService.postActiveFriendGroup(friendgroupid).subscribe(data => {
	this.router.navigate(['friendgroup', friendgroupid]);
    });
    this.router.navigate(['loadingpage']);
  }
}
