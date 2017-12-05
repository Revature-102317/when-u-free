import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';

import { AdminComponent }           from './admin.component';
import { AdminDashboardComponent }  from './admin-dashboard.component';
//import { ManageSettingsComponent }    from './manage-settings.component';
import { ManageGroupsComponent }    from './manage-friendgroups.component';

import { AdminRoutingModule }       from './admin-routing.module';

@NgModule({
	imports: [
		CommonModule,
		AdminRoutingModule
	],
	declarations: [
		AdminComponent,
		AdminDashboardComponent,
	//	ManageSettingsComponent,
		ManageGroupsComponent
	]
})
export class AdminModule {}
