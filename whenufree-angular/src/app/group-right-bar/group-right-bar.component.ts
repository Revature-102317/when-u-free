import { Component, OnInit, Input } from '@angular/core';

import {GroupBestTime} from '../domain/groupBestTime';
import {GroupFreeTime} from '../domain/groupFreeTime';
import {ActivatedRoute, Router} from '@angular/router';
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
                private router: Router,
                private route: ActivatedRoute) { }

    ngOnInit() {
      this.userService.getUser().subscribe(
        user => this.currentUser = user,
        error => this.router.navigate([''])
      );
      this.getTimes();
    }

  getTimes() {
    this.route.params.subscribe(params => {
      let id = +params['id'];
      this.groupuserService.getGroupFreeTimes(id).subscribe(greatTimes => {
        this.groupFreeTimes = greatTimes;
        this.getGreatTimes();
      });
    });
  }

    getGreatTimes() {
      this.route.params.subscribe(params => {
        let id = +params['id'];
        this.groupuserService.getGroupFreeTimesBetter(id).subscribe(greatTimes => {
          this.greatTimes = greatTimes;
        });
      });
    }

    toGroupSettings() {
    }

    toScheduleEvent() {
    }

}
