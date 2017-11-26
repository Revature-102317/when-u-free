import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
//import { jqxSchedulerComponent } from '../../../jqwidgets-ts/angular_jqxscheduler';

@Component({
  selector: 'app-settime',
  templateUrl: './settime.component.html',
  styleUrls: ['./settime.component.css']
})
export class SettimeComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
  }

}
