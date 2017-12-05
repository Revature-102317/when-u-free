import {Component, OnInit, AfterContentInit, AfterViewInit} from '@angular/core';
import {TimeSlot} from '../domain/timeSlot';
import {SettimeService} from '../services/settime.service';
import {Times} from './times';
import {TIMES} from './times';
import { UserService} from '../services/user.service';
import {Router} from '@angular/router';
import {User} from '../domain/user';

/*****
 *
 * This component sets your default times
 * If you want to set other things, things, we will set them
 *
 */
@Component({
  selector: 'app-settimescheduled',
  templateUrl: './settimescheduled.component.html',
  styleUrls: ['./settimescheduled.component.css']
})
export class SettimescheduledComponent implements OnInit, AfterContentInit, AfterViewInit {
  timeslots: TimeSlot[] = [];

  times: Times[] = TIMES;
  days: String[] = ['SU/', 'MO/', 'TU/', 'WE/', 'TH/', 'FR/', 'SA/'];

  // This will be subscribed to the current times set by the user
  userDefaultTimes: TimeSlot[] = [];

  userScheduledTimes: TimeSlot[] = [];

  selected = [];

  currentUser: User;

  constructor(private settimeService: SettimeService,
              private userService: UserService,
              private router: Router) { }

  ngOnInit() {
    this.userService.getUser().subscribe(
      user => this.currentUser = user,
      error => this.router.navigate([''])
    );
    this.getTimeSlots();
    this.setDefaultTime('deleteDefaultTimeToServerForAjaxList');
  }

  ngAfterContentInit() {

  }

  ngAfterViewInit() {
    this.getUserDefaultTimes();
    this.getScheduledTime();
  }

// Gets the entire timeslot
  getTimeSlots() {
    this.settimeService.getTimes().subscribe(timeslots => {
      this.timeslots = timeslots;
      for (let entry of this.timeslots) {
        this.selected[entry.dateTime] = false;
      }
    });
  }
// Setting each time individually. AJAX request. When clicking a box, this will send a request
  setDefaultTime(weektime: string) {
    this.settimeService.setDefaultTime(weektime).subscribe(data => {});
    console.log(weektime);
  }
// Submitting scheduled to the database
  submitDefault(submit: string) {
    this.settimeService.submitDefaultTime(submit).subscribe(data => {
      //this.reload();
      this.router.navigate(['settime']);
    });
    this.router.navigate(['loadingpage']);
    console.log(submit);
  }

// Subscribing the default user free times to the list userDefaultTimes
// This also sets and resets everything
  getUserDefaultTimes() {
    this.settimeService.getUserDefaultTime().subscribe(defaultTimes => {
        this.userDefaultTimes = defaultTimes;
        for (let entry of this.userDefaultTimes) {
          var property = document.getElementById(entry.dateTime);
          property.style.backgroundColor = '#86c6f9';
        }
      this.populateDefault();
    });
    return this.userDefaultTimes;
  }
// makes the default properties light blue
  populateDefault() {
    for (let entry of this.userDefaultTimes) {
      var property = document.getElementById(entry.dateTime);
      property.style.backgroundColor = '#86c6f9';
    }
  }
  getScheduledTime() {
    this.settimeService.getUserScheduledTime().subscribe(scheduledTimes => {
      this.settimeService.getTimes().subscribe(timeslots => {
        this.timeslots = timeslots;
        this.userScheduledTimes = scheduledTimes;
        for (let entry of this.timeslots) {
          this.selected[entry.dateTime] = false;
        }
        for (let entry of this.userScheduledTimes) {
          this.selected[entry.dateTime] = true;
          this.setDefaultTime(entry.dateTime + false);
        }
      });
    });
  }
}

