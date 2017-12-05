import { Component, OnInit } from '@angular/core';
import {HomepageService} from '../services/homepage.service';
import {SocialNetworkService} from '../services/social-network.service'
import {User} from '../domain/user';
import { UserService } from '../services/user.service';
import {Router} from '@angular/router';
import {Named} from '../domain/named';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
    
    
    currentUser: User = null;
    friendRequests: Named[] = [];
    groupInvites: Named[] = [];
    
    constructor(private homepageService: HomepageService,
		private snService: SocialNetworkService,
		private userService: UserService,
		private router: Router) { }

    ngOnInit() {	
	this.getUser();
    }

    populateRequests(){
	this.friendRequests = [];
	for(let friend of this.currentUser.friendsList){
	    if(friend.status === "reverse-pending"){
		this.snService.getUser(friend.friendId).subscribe(
		    data => this.friendRequests.push(data));
	    }
	}
    }

    populateInvites(){
	this.groupInvites = [];
	for(let conn of this.currentUser.connections){
	    if(conn.status === "invited"){
		this.snService.getFriendGroup(conn.friendGroupId).subscribe(
		    data => this.groupInvites.push(data)
		);
	    }
	}
    }

    getUser() {
	this.userService.getUser().subscribe(
	    user => {
		this.currentUser = user;
		this.populateRequests();
		this.populateInvites();
	    },
	    error => this.router.navigate([''])
	);
    }

    onAcceptFriend(friend: Named){
	this.snService.acceptFriend(friend).subscribe(data => {
	    this.getUser();
	});

    }

    onDenyFriend(friend: Named){
	//Denying friend request and removing a friend are functionally the same
	this.snService.removeFriend(friend).subscribe(data => {
	    this.getUser();
	});
	
    }
    onAcceptGroup(group: Named){
	this.snService.acceptGroup(group).subscribe(data => {
	    this.getUser();
	});
    }
    onDenyGroup(group: Named){
	this.snService.leaveGroup(group).subscribe(data => {
	    this.getUser();
	});
    }
}
