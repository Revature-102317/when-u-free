import { Component, OnInit } from '@angular/core';
import {HomepageService} from '../services/homepage.service';
import {User} from '../domain/user';
import {AuthenticationService} from "../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  users: User[] = [];

  currentUser: User;
  constructor(private homepageService: HomepageService,
              private authService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
    this.authService.getUser().subscribe(
      user => this.currentUser = user,
      error => this.router.navigate([''])
    );
    this.getUser();
  }

  getUser() {
    this.homepageService.getCurrentUser().subscribe(users => this.users = users);
  }
}
