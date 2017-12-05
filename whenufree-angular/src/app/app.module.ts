import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import {HomepageService} from './services/homepage.service';
import {AuthenticationService} from './services/authentication.service';
import {SpringXsrfInterceptor} from './interceptors/springXsrfInterceptor';
import {SocialNetworkService} from './services/social-network.service';
import {UrlService} from './services/url.service';
import {RegistrationService} from './services/registration.service';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomepageComponent } from './homepage/homepage.component';
import { NavbarComponent } from './navbar/navbar.component';
import { WhenufreeRoutingModule } from './whenufree-routing.module';
import { SettimeComponent } from './settime/settime.component';
import { SearchComponent } from './search/search.component';
import { RegistrationComponent } from './registration/registration.component';
import {SettimeService} from './services/settime.service';
import { UserService } from './services/user.service';
import { SettimescheduledComponent } from './settimescheduled/settimescheduled.component';
import { SettimenavigatorComponent } from './settimenavigator/settimenavigator.component';
import { LoadingpageComponent } from './loadingpage/loadingpage.component';
import { MessageService } from './message.service';
import { GroupUserService} from "./services/groupuser.service";
import { RegistrationSuccessComponent } from './registration-success/registration-success.component';
import { PageNotFoundComponent } from './page-not-found.component';
import { AdminModule } from './admin/admin.module';
import { SettingsModule } from './settings/settings.module';
import { FriendGroupsModule } from './friendgroups/friendgroups.module';

@NgModule({
  declarations: [
      AppComponent,
      LoginComponent,
      HomepageComponent,
      NavbarComponent,
      SettimeComponent,
      SettimescheduledComponent,
      SettimenavigatorComponent,
      SearchComponent,
      RegistrationComponent,
      LoadingpageComponent,
      RegistrationSuccessComponent,
      PageNotFoundComponent
  ],
  imports: [
      BrowserModule,
      HttpClientModule,
      FormsModule,
      ReactiveFormsModule,
	  AdminModule,
	  SettingsModule,
	  FriendGroupsModule,
      WhenufreeRoutingModule
  ],
    providers: [HomepageService,
		UserService,
		SettimeService,
		AuthenticationService,
		SocialNetworkService,
		UrlService,
		MessageService,
		UserService,
		GroupUserService,
		RegistrationService,
		{provide: HTTP_INTERCEPTORS,
		 useClass: SpringXsrfInterceptor,
		 multi: true}],
    bootstrap: [AppComponent]
})
export class AppModule { }
