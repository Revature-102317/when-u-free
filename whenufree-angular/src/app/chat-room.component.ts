import { Component, OnInit } from '@angular/core';

@Component ({
	selector: 'app-chat-room',
	template: `
	<head>
	<title>Chat - Customer Module</title>
	</head>

	<div id="wrapper">
	<div id="menu">
	<p class="welcome">Welcome, <b></b></p>
	<p class="logout"><a id="exit" href="#">Exit Chat</a></p>
	<div style="clear:both"></div>
	</div>

	<div id="chatbox"></div>

	<form name="message" action="">
	<input name="usermsg" type="text" id="usermsg" size="63" />
	<input name="submitmsg" type="submit"  id="submitmsg" value="Send" />
	</form>
	</div>
`
})
export class ChatRoomComponent implements OnInit {
	messages: string [] = []
	message: string;

	friendGroup: FriendGroup;
	user: User;

	constructor () { }

	onInit( groupUserService: GroupuserService, userService: UserService) { 
		this.groupUserService.postActiveFriendGroup('id').subscribe.(
			success => {
				friendgroup => this.friendGroup = friendGroup;
			},
			error => {
				console.log("you fucked up");
			}
		);
		this.userService.getUser().subscribe(
			success => {
				user => this.user = user;
			},
			error => {
				console.log("you fucked up");
			}
		);
		this.Messages = this.friendGroup.messages;
	}

	sendMessage(message: Message) {
		this.Messages
	}

}
