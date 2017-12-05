import { Component, OnInit } from '@angular/core';

import {SettimeService} from '../services/settime.service';
import {SchedulingService} from '../services/scheduling.service';
@Component({
  selector: 'app-scheduling',
  templateUrl: './scheduling.component.html',
  styleUrls: ['./scheduling.component.css']
})
export class SchedulingComponent implements OnInit {

    days: string[] = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    times: string[] = [];
    durations: number[] = [];

    selectedDay: string = 'Sunday';
    startTime: string = '00:00';
    selectedDuration: number = 1;
    
    constructor(private stService: SettimeService) { }

    ngOnInit() {
	this.getTimeSlots();
    }

    getTimeSlots(){
	this.stService.getTimes().subscribe(
	    data => {
		for(let i = 0; i < 24; i++){
		    this.times.push(data[i].dateTime.substring(3,8));
		    this.durations.push(i + 1);
		}
	    });	
    }

    onSubmit(){
	var schedulerObj = {'day' : this.days.indexOf(this.selectedDay),
			'time' : this.times.indexOf(this.startTime),
			'duration' : this.selectedDuration};
	
    }

}
