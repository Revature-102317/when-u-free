import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {HomepageService} from './services/homepage.service';
import { HomepageComponent } from './homepage/homepage.component';
import { NavbarComponent } from './navbar/navbar.component';
import { WhenufreeRoutingModule } from './whenufree-routing/whenufree-routing.module';
import { SettimeComponent } from './settime/settime.component';
import { SearchComponent } from './search/search.component';
import { GroupRightBarComponent } from './group-right-bar/group-right-bar.component';
import { RegistrationComponent } from './registration/registration.component';

@NgModule({
  declarations: [
    AppComponent,
      LoginComponent,
    HomepageComponent,
    NavbarComponent,
    SettimeComponent,
    SearchComponent,
    GroupRightBarComponent,
    RegistrationComponent
  ],
  imports: [
      BrowserModule,
      HttpClientModule,
      FormsModule,
      WhenufreeRoutingModule
  ],
  providers: [HomepageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
