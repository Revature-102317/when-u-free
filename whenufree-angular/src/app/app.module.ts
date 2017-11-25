import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
<<<<<<< HEAD
<<<<<<< HEAD
import {HttpClientModule} from '@angular/common/http';
=======
import { LoginComponent } from './login/login.component';
>>>>>>> 78cc519364ce84a24e404c29a15819d0438042fa
import {HomepageService} from './services/homepage.service';
import { HomepageComponent } from './homepage/homepage.component';
import { NavbarComponent } from './navbar/navbar.component';
import { WhenufreeRoutingModule } from './whenufree-routing/whenufree-routing.module';
import { SettimeComponent } from './settime/settime.component';
<<<<<<< HEAD

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    NavbarComponent,
    SettimeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    WhenufreeRoutingModule
  ],
  providers: [HomepageService],
=======
=======
import { SearchComponent } from './search/search.component';
import { GroupRightBarComponent } from './group-right-bar/group-right-bar.component';
import { RegistrationComponent } from './registration/registration.component';
>>>>>>> 78cc519364ce84a24e404c29a15819d0438042fa


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
<<<<<<< HEAD
  providers: [],
>>>>>>> 7455236ffc92a37797a2b1541effa66be6a2f83d
=======
  providers: [HomepageService],
>>>>>>> 78cc519364ce84a24e404c29a15819d0438042fa
  bootstrap: [AppComponent]
})
export class AppModule { }
