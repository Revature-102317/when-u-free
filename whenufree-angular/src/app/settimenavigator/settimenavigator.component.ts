import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../services/authentication.service';

@Component({
  selector: 'app-settimenavigator',
  templateUrl: './settimenavigator.component.html',
  styleUrls: ['./settimenavigator.component.css']
})
export class SettimenavigatorComponent implements OnInit {

  constructor(private authService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
  }

}
