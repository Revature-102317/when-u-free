import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { GroupUserService } from '../../services/groupuser.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { FriendGroup } from '../../domain/friendgroup';
import { Observable } from 'rxjs/Observable';
//import { PageOption } from '../friendgroups';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-group-select',
  template: `
  <h2>Your Friend Groups</h2>
  <ul class="items">
    <li *ngFor="let friendgroup of friendgroups$ | async"
      [class.selected]="friendgroup.id === selectedId" class="rounded-list">
      <a [routerLink]="['/groups', friendgroup.friendgroupid]">
        <span class="badge">{{friendgroup.friendgroupId}}
        </span>{{ friendgroup.name}}
      </a>
    </li>
  </ul>
`,
  styleUrls: ['./group-select.component.css']
})
export class GroupSelectComponent implements OnInit {
  friendgroups$: Observable<FriendGroup[]>;
  private selectedId: number;
  //@Input() page: PageOption;
  //@Output() onPageChange = new EventEmitter<PageOption>();
  //@Output() onFriendSelect = new EventEmitter<string>();
  
  constructor(private service: GroupUserService,
              private route: ActivatedRoute) { }

  ngOnInit() {
	  /*
    this.userService.getUser().subscribe(
      user => this.currentUser = user,
      error => this.router.navigate([''])
    );
    this.getFriendGroups();
	*/
   this.friendgroups$ = this.route.paramMap
		.switchMap(( params: ParamMap) => {
			this.selectedId = +params.get('id');
			return this.service.getFriendGroups();
		});
  }

  /*
  getFriendGroups() {
    this.groupuserService.getFriendGroups().subscribe(friendgroups => this.friendgroups = friendgroups);
  }

  postFriendGroup(friendgroup: string) {
	this.onFriendSelect.emit(friendgroup);
    this.groupuserService.postActiveFriendGroup(friendgroup).subscribe(
		data => {
			this.router.navigate(['friendgroup']);
			this.onPageChange.emit(this.page.home);
		});
		// this.router.navigate(['loadingpage']);
	}
	*/
}
