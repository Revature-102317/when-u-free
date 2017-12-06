import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/user.service';
import {GroupuserService} from '../services/groupuser.service';
import {User} from '../domain/user';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-group-user-bar',
  templateUrl: './group-user-bar.component.html',
  styleUrls: ['./group-user-bar.component.css']
})
export class GroupUserBarComponent implements OnInit {

  currentUser: User;

  users: User[] = [];

  constructor(private groupuserService: GroupuserService,
              private userService: UserService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.userService.getUser().subscribe(
      user => this.currentUser = user,
      error => this.router.navigate([''])
    );
    this.getUsers();
  }

  getUsers() {
    this.route.params.subscribe(params => {
      let id = +params['id'];
      this.groupuserService.getUsers(id).subscribe(users => {
        this.users = users;
      });
    });
  }
}
