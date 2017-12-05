import { Component, OnInit, HostBinding } from '@angular/core';
import {GroupUserService} from '../../services/groupuser.service';
import {User} from '../../domain/user';
import {UserService} from '../../services/user.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import {FriendGroup} from '../../domain/friendgroup';
import 'rxjs/add/operator/switchMap';
import { slideInDownAnimation } from '../../animations';

/*****
 *
 * This component is the component for each individual friendgroup
 *
 */

@Component({
	selector: 'app-group-home',
	templateUrl: './group-home.component.html',
	styleUrls: ['./group-home.component.css']
})
export class GroupHomeComponent implements OnInit {
	@HostBinding('@routeAnimation') routeAnimation = true;
	@HostBinding('style.display')   display = 'block';
	@HostBinding('style.position')  position = 'absolute';
	groupName: string;
	friendGroup: FriendGroup;

	constructor(
		private groupuserService: GroupUserService,
		private userService: UserService,
		private route: ActivatedRoute,
		private router: Router
	) { }

	ngOnInit() {
		/*
		   this.userService.getUser().subscribe(
		   user => this.currentUser = user,
		   error => this.router.navigate([''])
		   );
		 */
		this.route.data
			.subscribe(( data: {friendGroup: FriendGroup}) => {
				this.groupName = data.friendGroup.name;
				this.friendGroup = data.friendGroup;
			});
	}

	/*
	getFriendGroup() {
		const groupName = +this.route.snapshot.paramMap.get('id');
		this.groupuserService.getActiveFriendGroup().subscribe(friendgroup => {
			this.currentFriendGroup = friendgroup;
		});
	}
	*/

   /*
	gotoFriendGroups() {
		let friendGroupId = this.friendGroup? friendGroup.friendgroupid : null;
		this.router.navigate(['../', { id: friendGroupId}],
							 { relativeTo: this.route});
	}
	*/
}
