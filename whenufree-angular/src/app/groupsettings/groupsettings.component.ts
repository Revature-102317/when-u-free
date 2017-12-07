import { Component, OnInit } from '@angular/core';
import {GroupuserService} from '../services/groupuser.service';
import {UserService} from '../services/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Friendgroup} from '../domain/friendgroup';

@Component({
  selector: 'app-groupsettings',
  templateUrl: './groupsettings.component.html',
  styleUrls: ['./groupsettings.component.css']
})
export class GroupsettingsComponent implements OnInit {

  currentFriendGroup: Friendgroup;

  isAdmin = false;

  constructor(private groupuserService: GroupuserService,
              private userService: UserService,
              private router: Router,
              private route: ActivatedRoute) {}

    ngOnInit() {
	this.getIsAdmin();
    }

  leaveGroup() {
    this.route.params.subscribe(params => {
      let id = +params['id'];
      this.groupuserService.leaveGroup(id).subscribe(data => {
        this.router.navigate(['friendgroups']);
      });
    });
  }

  getIsAdmin(){
    this.route.params.subscribe(params =>{
      let id = +params['id'];
      this.userService.getUser().subscribe(
        user => {
          for (let connection of user.connections){
            if (connection.friendGroupId === id &&
              connection.isAdmin === true) {
              this.isAdmin = true;
            }
          }
        }
      );
    });

  }

}
