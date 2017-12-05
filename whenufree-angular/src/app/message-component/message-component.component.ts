import { Component, OnInit, Input } from '@angular/core';
import { Message } from '../domain/message';
import { User } from '../domain/user';
import { Friendgroup } from '../domain/friendgroup';
import { GroupuserService } from '../services/groupuser.service';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';


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
	  private router: Router) { }

  ngOnInit() {
	  this.id$ = this.router.paramMap
		.switchMap(( params: ParamMap) =>
			this.groupUserService.getMessages( params.get('id')));
  }
}
