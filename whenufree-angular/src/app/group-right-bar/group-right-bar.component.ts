import { Component, OnInit, Input } from '@angular/core';

import {GroupBestTime} from '../domain/groupBestTime'

@Component({
  selector: 'app-group-right-bar',
  templateUrl: './group-right-bar.component.html',
  styleUrls: ['./group-right-bar.component.css']
})
export class GroupRightBarComponent implements OnInit {

    @Input() groupId: number;

    bestTimes: GroupBestTime[];
    
    constructor() { }

    ngOnInit() {
    }

    toGroupSettings(){
    }

    toScheduleEvent(){
    }

}
