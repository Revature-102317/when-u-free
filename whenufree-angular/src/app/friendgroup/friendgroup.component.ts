import { Component, OnInit } from '@angular/core';
import {GroupuserService} from '../services/groupuser.service';
import {User} from '../domain/user';
import {UserService} from '../services/user.service';
import {Router} from '@angular/router';
import {Friendgroup} from '../domain/friendgroup';

/*****
 *
 * This component is the component for each individual friendgroup
 *
 */

@Component({
  selector: 'app-friendgroup',
  templateUrl: './friendgroup.component.html',
  styleUrls: ['./friendgroup.component.css']
})
export class FriendgroupComponent implements OnInit {

  currentUser: User;

  currentFriendGroup: Friendgroup;

  constructor(private groupuserService: GroupuserService,
              private userService: UserService,
              private router: Router) { }

  ngOnInit() {
    this.userService.getUser().subscribe(
      user => this.currentUser = user,
      error => this.router.navigate([''])
    );
    this.getFriendGroup();
  }

  getFriendGroup() {
    this.groupuserService.getActiveFriendGroup().subscribe(friendgroup => {
      this.currentFriendGroup = friendgroup;
    });
  }

}
