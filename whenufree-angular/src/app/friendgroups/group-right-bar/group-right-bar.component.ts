import { Component, OnInit, Input } from '@angular/core';

import {GroupBestTime} from '../../domain/groupBestTime';
import {GroupFreeTime} from '../../domain/groupFreeTime';
import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';
import {GroupUserService} from '../../services/groupuser.service';
import {User} from '../../domain/user';

@Component({
  selector: 'app-group-right-bar',
  templateUrl: './group-right-bar.component.html',
  styleUrls: ['./group-right-bar.component.css']
})
export class GroupRightBarComponent implements OnInit {
  groupFreeTimes: GroupFreeTime[] = [];

    @Input() groupId: number;

    currentUser: User;

    bestTimes: GroupBestTime[];

    constructor(private groupuserService: GroupUserService,
                private userService: UserService,
                private router: Router) { }

    ngOnInit() {
      this.userService.getUser().subscribe(
        user => this.currentUser = user,
        error => this.router.navigate([''])
      );
      this.getGroupFreeTimes();
    }

    getGroupFreeTimes() {
      this.groupuserService.getGroupFreeTimes().subscribe(groupfreetimes => {
        this.groupFreeTimes = groupfreetimes;
      });
    }

    toGroupSettings() {
    }

    toScheduleEvent() {
    }

}
