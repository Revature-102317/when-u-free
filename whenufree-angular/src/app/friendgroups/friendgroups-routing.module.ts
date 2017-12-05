import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FriendGroupsComponent } from './friendgroups.component';
import { GroupSelectComponent } from './group-select/group-select.component';
import { GroupHomeComponent } from './group-home/group-home.component';
import { GroupMenuComponent } from './group-menu/group-menu.component';
import { GroupHomeResolver } from './friendgroups-home-resolver.service';

const friendGroupsRoutes: Routes = [
	{
		path: 'groups',
		component: FriendGroupsComponent,
		children: [
			{
				path: '',
				component: GroupSelectComponent,
				children: [
					{
						path: ':id',
						component: GroupHomeComponent,
						children: [
							{
								path: '',
								component: GroupMenuComponent
							}
						]
					}
				]
			}
		]
	}
];

@NgModule({
	imports: [
		RouterModule.forChild( friendGroupsRoutes)
	],
	exports: [
		RouterModule
	],
	providers: [
		GroupHomeResolver
	]
})
export class FriendGroupsRoutingModule { }
