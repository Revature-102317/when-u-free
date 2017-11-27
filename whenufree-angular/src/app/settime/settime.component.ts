import { Component, OnInit } from '@angular/core';
import {TimeSlot} from '../domain/timeSlot';
import {SettimeService} from '../services/settime.service';
import {Times} from './times';
import {TIMES} from './times';

@Component({
  selector: 'app-settime',
  templateUrl: './settime.component.html',
  styleUrls: ['./settime.component.css']
})
export class SettimeComponent implements OnInit {
  timeslots: TimeSlot[] = [];

  times: Times[] = TIMES;

  constructor(private settimeService: SettimeService) { }

  ngOnInit() {
    this.getTimeSlots();
  }

     getTimeSlots() {
         this.settimeService.getTimes().subscribe(timeslots => this.timeslots = timeslots);
  }
}
