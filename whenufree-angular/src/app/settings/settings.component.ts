import { Component, OnInit } from '@angular/core';
import { User } from '../domain/user';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
@Input() user: User;

  constructor(
	private authentication: AuthenticationService) { }

  ngOnInit() {
    this.getHero();
  }

  getHero(): void {
    authentication.getUser.subscribe(user => this.user = user);
  }

}
