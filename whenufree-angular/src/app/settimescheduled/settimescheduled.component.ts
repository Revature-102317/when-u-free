import {Component, OnInit, AfterContentInit, AfterViewInit} from '@angular/core';
import {TimeSlot} from '../domain/timeSlot';
import {SettimeService} from '../services/settime.service';
import {Times} from './times';
import {TIMES} from './times';
import {AuthenticationService} from '../services/authentication.service';
import {Router} from '@angular/router';
import {User} from '../domain/user';
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

  selected = [];
  defaultTime: string[] = [];

  currentUser: User;

  constructor(private settimeService: SettimeService,
              private authService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
    //this.submitDefault('deleteDefaultTimesToTheserver')
    this.authService.getUser().subscribe(
      user => this.currentUser = user,
      error => this.router.navigate([''])
    );
    this.getTimeSlots();
  }

  ngAfterContentInit() {
  }

  ngAfterViewInit() {
    this.getUserDefaultTimes();
  }

// Gets the entire timeslot
  getTimeSlots() {
    this.settimeService.getTimes().subscribe(timeslots => this.timeslots = timeslots);
  }
// Setting each time individually. AJAX request
  setDefaultTime(weektime: string) {
    this.settimeService.setDefaultTime(weektime).subscribe(data => {});
    console.log(weektime);
  }
// Submitting default to the database
  submitDefault(submit: string) {
    this.settimeService.submitDefaultTime(submit).subscribe(data => {});
    console.log(submit);
  }

// Subscribing the default user free times to the list userDefaultTimes
  getUserDefaultTimes() {
    this.settimeService.getUserDefaultTime().subscribe(defaultTimes => {
      this.userDefaultTimes = defaultTimes;
      for (let entry of this.userDefaultTimes) {
        this.selected[entry.dateTime] = true;
        this.setDefaultTime(entry.dateTime);
      }
      this.populateDefault();
    });
    return this.userDefaultTimes;
  }

  populateDefault() {
    for (let entry of this.userDefaultTimes) {
      var property = document.getElementById(entry.dateTime);
      property.style.backgroundColor = '#86c6f9';
    }
  }
}

