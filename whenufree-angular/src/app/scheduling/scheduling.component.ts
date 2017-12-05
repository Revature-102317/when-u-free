import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {UserService} from '../services/user.service';
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

    isAdmin = false;
    
    constructor(private stService: SettimeService,
	        private route: ActivatedRoute,
		private router: Router,
		private schService: SchedulingService,
		private userService: UserService) { }

    ngOnInit() {
	this.getTimeSlots();
	this.getIsAdmin();
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

    getIsAdmin(){
	this.route.params.subscribe(params =>{
	    let id = +params['id'];
	    this.userService.getUser().subscribe(
		user => {
		    for(let connection of user.connections){
			if(connection.friendGroupId === id &&
			   connection.isAdmin === true){
			    this.isAdmin = true;
			}
		    }
		}
	    );
	});
	
    }

    onSubmit(){
	console.log("entered");
	this.route.params.subscribe(params =>{
	    let id = +params['id'];
	    let schedulerObj = {
		'groupId' : id,
		'day' : this.days.indexOf(this.selectedDay),
		'time' : this.times.indexOf(this.startTime),
		'duration' : this.selectedDuration};
	    console.log(JSON.stringify(schedulerObj));
	    this.schService.scheduleEvent(schedulerObj).subscribe(
		data => {}
	    );
	});
    }

}
