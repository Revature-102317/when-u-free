import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../services/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  template: `
  <ul>
  <li><a routerLink = "/homepage/" routerLinkActive="active">Homepage</a></li>
  <li><a routerLink="/groups/" routerLinkActive="active">Friend Groups</a></li>
  <li><a routerLink = "/settime/" routerLinkActive="active">Set Times</a></li>
  <li><a routerLink = "/settings/" routerLinkActive="active">Settings</a></li>
  <li><a routerLink = "/admin" routerLinkActive="active">Admin</a></li>
  <li><a (click)="logout()" routerLinkActive="active" routerLink="/login">Logout</a></li>
</ul>
`,
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private authService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
  }

  logout() {
    this.authService.logout().subscribe(
      resp => this.router.navigate([''])
    );
  }

}
