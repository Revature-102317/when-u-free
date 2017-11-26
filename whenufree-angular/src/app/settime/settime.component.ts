import { Component, OnInit } from '@angular/core';
import {TimeSlot} from '../domain/timeSlot';
import {SettimeService} from '../services/settime.service';

@Component({
  selector: 'app-settime',
  templateUrl: './settime.component.html',
  styleUrls: ['./settime.component.css']
})
export class SettimeComponent implements OnInit {
  timeslots: TimeSlot[] = [];

  constructor(private settimeService: SettimeService) { }

  ngOnInit() {
    this.getTimeSlots();
  }

     getTimeSlots() {
         this.settimeService.getTimes().subscribe(timeslots => this.timeslots = timeslots);
  }
}
