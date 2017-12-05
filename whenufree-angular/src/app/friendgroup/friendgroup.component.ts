import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {GroupuserService} from '../services/groupuser.service';
import {User} from '../domain/user';
import {UserService} from '../services/user.service';

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
              private route: ActivatedRoute,
	      private router: Router) { }

  ngOnInit() {
    this.userService.getUser().subscribe(
      user => this.currentUser = user,
      error => this.router.navigate([''])
    );
    this.getFriendGroup();
  }

    getFriendGroup() {
	this.route.params.subscribe(params =>{
	    let id = +params['id'];
	    this.groupuserService.getFriendGroup(id).subscribe(friendgroup => {
		this.currentFriendGroup = friendgroup;
	    });
	});
				    
  }

}
