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

  hideForm: boolean = true;

  currentUser: User;

  text: string = '';

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
      this.router.navigate(['friendgroup', friendgroupid]);
  }

  createFriendGroup(id: string) {
    this.groupuserService.createFriendGroup(id).subscribe(data => {
      this.router.navigate(['friendgroups']);
    });
    this.router.navigate(['loadingpage']);
  }

  showForm() {
    if (this.hideForm === true) {
      this.hideForm = false;
    }else {
      this.hideForm = true;
    }
  }
}
