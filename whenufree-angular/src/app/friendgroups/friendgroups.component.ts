import { Component, OnInit } from '@angular/core';
import {GroupuserService} from '../services/groupuser.service';
import {UserService} from '../services/user.service';
import {Router} from '@angular/router';
import {Friendgroup} from '../domain/friendgroup';
import {User} from '../domain/user';

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

}
