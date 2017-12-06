import { Component, OnInit, Input } from '@angular/core';
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
export class MessageComponentComponent implements OnInit {
	messages: Message [];
	message: Message;
	user: User;
	friendGroup: Friendgroup;
	id$: number;
	

  constructor(
	  private groupUserService: GroupuserService,
	  private userService: UserService,
	  private router: Router,
	  private route: ActivatedRoute
  ) { }

  ngOnInit() {
	  this.route.params.subscribe( params => {
		  let id = +params['id'];
		  this.groupUserService.getMessages(id).subscribe(
			  messages => this.messages = messages);
	  });
  }

  sendMessage(message: Message) {
	  message
	  this.groupUserService.sendMessage(message).subscribe(
		  success => this.message = '');
	  }
}
