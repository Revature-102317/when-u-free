import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-group-right-bar',
  templateUrl: './group-right-bar.component.html',
  styleUrls: ['./group-right-bar.component.css']
})
export class GroupRightBarComponent implements OnInit {

    @Input() groupId: number;

    bestTimes;
    
    constructor() { }

    ngOnInit() {
    }

    toGroupSettings(){
    }

    toScheduleEvent(){
    }

}
