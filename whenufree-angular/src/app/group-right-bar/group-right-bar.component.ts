import { Component, OnInit, Input } from '@angular/core';

import {GroupBestTime} from '../domain/groupBestTime';
import {GroupFreeTime} from '../domain/groupFreeTime';
import {Router} from '@angular/router';
import {UserService} from '../services/user.service';
import {GroupuserService} from '../services/groupuser.service';
import {User} from '../domain/user';
import {Named} from '../domain/named';

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

    greatTimes: Named[] = [];

    constructor(private groupuserService: GroupuserService,
                private userService: UserService,
                private router: Router) { }

    ngOnInit() {
      this.userService.getUser().subscribe(
        user => this.currentUser = user,
        error => this.router.navigate([''])
      );
      this.getGroupFreeTimes();
      this.getGreatTimes();
    }

    getGroupFreeTimes() {
      this.groupuserService.getGroupFreeTimes().subscribe(groupfreetimes => {
        this.groupFreeTimes = groupfreetimes;
      });
    }

    getGreatTimes() {
      this.groupuserService.getGroupFreeTimesBetter().subscribe(greatTimes => {
        this.greatTimes = greatTimes;
      });
    }

    toGroupSettings() {
    }

    toScheduleEvent() {
    }

}
