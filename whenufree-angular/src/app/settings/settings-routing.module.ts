import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { SettingsComponent } from './settings.component';
import { UserInfoComponent, UserFormComponent } from './user-form.component';

const settingsRoutes: Routes = [
	{ path: 'settings', component: SettingsComponent },
];

@NgModule({
	imports: [
		RouterModule.forChild( settingsRoutes)
	],
	exports: [
		RouterModule
	]
})
export class SettingsRoutingModule { }
