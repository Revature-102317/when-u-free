import { Component, OnInit } from '@angular/core';
import {HomepageService} from '../services/homepage.service';
import {User} from '../domain/user';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  users: User[] = [];

  constructor(private homepageService: HomepageService) { }

  ngOnInit() {
    this.getUser();
  }

  getUser() {
    this.homepageService.getCurrentUser().subscribe(users => this.users = users);
  }
}
