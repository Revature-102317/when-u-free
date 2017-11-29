import { Component, OnInit } from '@angular/core';
import {TimeSlot} from '../domain/timeSlot';
import {SettimeService} from '../services/settime.service';
import {Times} from './times';
import {TIMES} from './times';
import {AuthenticationService} from '../services/authentication.service';
import {Router} from '@angular/router';
import {User} from '../domain/user';

@Component({
  selector: 'app-settime',
  templateUrl: './settime.component.html',
  styleUrls: ['./settime.component.css']
})
export class SettimeComponent implements OnInit {
  timeslots: TimeSlot[] = [];

  ts: TimeSlot;

  times: Times[] = TIMES;
  days: String[] = ["SU/", "MO/", "TU/", "WE/", "TH/", "FR/", "SA/"];

  currentUser: User;

  constructor(private settimeService: SettimeService,
              private authService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
    this.authService.getUser().subscribe(
      user => this.currentUser = user,
      error => this.router.navigate([''])
    );
    //this.getTimeSlots();
    this.getTime();
    //this.setDefaultTime();
  }

  getTime() {
        this.settimeService.getTime().subscribe(ts => this.ts = ts);
  }
  getTimeSlots() {
         this.settimeService.getTimes().subscribe(timeslots => this.timeslots = timeslots);
  }

  setDefaultTime(weektime: string) {
          this.settimeService.setDefaultTime(weektime).subscribe(data => {});
  }

  submitDefault(submit: string) {
          this.settimeService.submitDefaultTime(submit).subscribe(data => {});
  }
}
