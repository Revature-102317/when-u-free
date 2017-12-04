import { Component, OnInit } from '@angular/core';

import {SettimeService} from '../services/settime.service';

@Component({
  selector: 'app-scheduling',
  templateUrl: './scheduling.component.html',
  styleUrls: ['./scheduling.component.css']
})
export class SchedulingComponent implements OnInit {

    days: string[] = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    times: string[] = [];
    durations: number[] = [];

    selectedDay: string;
    startTime: string;
    selectedDuration: string;
    
    constructor(private stService: SettimeService) { }

    ngOnInit() {
	this.getTimeSlots();
    }

    getTimeSlots(){
	stService.getTimes().subscribe(
	    data => {
		for(let i = 0; i < 24; i++){
		    this.times.push(data[i].dateTime.subString(3,8));
		    this.durations.push(i + 1);
		}
	    });
	
    }

}
