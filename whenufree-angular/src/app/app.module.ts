import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import {HomepageService} from './services/homepage.service';
import {AuthenticationService} from './services/authentication.service'
import {SpringXsrfInterceptor} from './interceptors/springXsrfInterceptor'

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomepageComponent } from './homepage/homepage.component';
import { NavbarComponent } from './navbar/navbar.component';
import { WhenufreeRoutingModule } from './whenufree-routing/whenufree-routing.module';
import { SettimeComponent } from './settime/settime.component';
import { SearchComponent } from './search/search.component';
import { GroupRightBarComponent } from './group-right-bar/group-right-bar.component';
import { RegistrationComponent } from './registration/registration.component';
import { GroupUserBarComponent } from './group-user-bar/group-user-bar.component';
import {SettimeService} from './services/settime.service';
import { FriendgroupsComponent } from './friendgroups/friendgroups.component';
import { SettingsComponent } from './settings/settings.component';
import { UserService } from './services/user.service';
import { UserFormComponent } from './settings/user-form.component';
import { UserInfoComponent } from './settings/user-form.component';
import { SettimescheduledComponent } from './settimescheduled/settimescheduled.component';
import { SettimenavigatorComponent } from './settimenavigator/settimenavigator.component';
import { LoadingpageComponent } from './loadingpage/loadingpage.component';
import { MessageService } from './message.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomepageComponent,
    FriendgroupsComponent,
    NavbarComponent,
    SettimeComponent,
    SettimescheduledComponent,
    SettimenavigatorComponent,
    SearchComponent,
    GroupRightBarComponent,
    RegistrationComponent,
    GroupUserBarComponent,
    FriendgroupsComponent,
    LoadingpageComponent
  ],
  imports: [
      BrowserModule,
      HttpClientModule,
      FormsModule,
      WhenufreeRoutingModule,
	  ReactiveFormsModule
  ],
    providers: [HomepageService,
<<<<<<< HEAD
		UserService,
		SettimeService,
		AuthenticationService,
		MessageService,
		{provide: HTTP_INTERCEPTORS,
		 useClass: SpringXsrfInterceptor,
		 multi: true}],
=======
      SettimeService,
      AuthenticationService,
{provide: HTTP_INTERCEPTORS,
useClass: SpringXsrfInterceptor,
multi: true}],
>>>>>>> 976a349e0e1f5c92f0a465cdb8a75e69e56e70cf
  bootstrap: [AppComponent]
})
export class AppModule { }
