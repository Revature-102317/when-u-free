import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
<<<<<<< HEAD
import {HttpClientModule} from '@angular/common/http';
import {HomepageService} from './services/homepage.service';
import { HomepageComponent } from './homepage/homepage.component';
import { NavbarComponent } from './navbar/navbar.component';
import { WhenufreeRoutingModule } from './whenufree-routing/whenufree-routing.module';
import { SettimeComponent } from './settime/settime.component';

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


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
>>>>>>> 7455236ffc92a37797a2b1541effa66be6a2f83d
  bootstrap: [AppComponent]
})
export class AppModule { }
