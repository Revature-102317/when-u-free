import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { SettingsComponent } from './settings.component';
import { UserInfoComponent, UserFormComponent } from './user-form.component';
import { SettingsRoutingModule } from './settings-routing.module';

import { UserService } from '../services/user.service';

@NgModule ({
	imports: [
		CommonModule,
		SettingsRoutingModule,
		ReactiveFormsModule
	],
	declarations: [
		SettingsComponent,
		UserInfoComponent,
		UserFormComponent
	],
	providers: [
		UserService
	]
})
export class SettingsModule { }
