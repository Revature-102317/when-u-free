import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import {GroupUserService} from '../../services/groupuser.service';
import {User} from '../../domain/user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-group-user-bar',
  templateUrl: './group-user-bar.component.html',
  styleUrls: ['./group-user-bar.component.css']
})
export class GroupUserBarComponent implements OnInit {

  currentUser: User;

  users: User[] = [];

  constructor(private groupuserService: GroupUserService,
              private userService: UserService,
              private router: Router) { }

  ngOnInit() {
    this.userService.getUser().subscribe(
      user => this.currentUser = user,
      error => this.router.navigate([''])
    );
    this.getUsers();
  }

  getUsers() {
    this.groupuserService.getUsers().subscribe(users => {
      this.users = users;
    });
  }

}
