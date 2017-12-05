import { NgModule }             from '@angular/core';
import { CommonModule } from '@angular/common';

import { FriendGroupsComponent } from './friendgroups.component';
import { GroupSelectComponent } from './group-select/group-select.component';
import { GroupHomeComponent } from './group-home/group-home.component';
import { GroupMenuComponent } from './group-menu/group-menu.component';
import { GroupHomeResolver } from './friendgroups-home-resolver.service';
import { GroupUserBarComponent } from './group-user-bar/group-user-bar.component';
import { GroupRightBarComponent } from './group-right-bar/group-right-bar.component';
 

import { FriendGroupsRoutingModule } from './friendgroups-routing.module';
import { GroupUserService } from '../services/groupuser.service';

@NgModule ({
	imports: [
		CommonModule,
		FriendGroupsRoutingModule
	],
	declarations: [
		FriendGroupsComponent,
		GroupSelectComponent,
		GroupHomeComponent,
		GroupMenuComponent,
		GroupRightBarComponent,
		GroupUserBarComponent
	],
	providers: [
		GroupUserService
	]
})
export class FriendGroupsModule { }
