import {Component, OnInit, Input, AfterViewInit} from '@angular/core';
import { Message } from '../domain/message';
import { User } from '../domain/user';
import { Friendgroup } from '../domain/friendgroup';
import { GroupuserService } from '../services/groupuser.service';
import { UserService } from '../services/user.service';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-message-component',
  templateUrl: './message-component.component.html',
  styleUrls: ['./message-component.component.css']
})
export class MessageComponentComponent implements OnInit, AfterViewInit {
  messages: Message [];
  message: string;
  user: User;
  friendGroup: Friendgroup;
  id$: number;


  constructor(private groupUserService: GroupuserService,
              private userService: UserService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.userService.getUser().subscribe(
      user => this.user = user,
      error => this.router.navigate([''])
    );
    this.route.params.subscribe(params => {
      let id = +params['id'];
      this.groupUserService.getMessages(id).subscribe(
        messages => this.messages = messages);
    });
  }


  ngAfterViewInit() {
  }

  sendMessage() {
    this.route.params.subscribe(params => {
      let id = +params['id'];
      this.groupUserService.sendMessage(id, this.message).subscribe(
        data => {});
      window.location.reload();
    });
  }
}
