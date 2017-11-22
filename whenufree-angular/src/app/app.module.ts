import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {HttpClientModule} from '@angular/common/http';
import {HomepageService} from './services/homepage.service';
import { HomepageComponent } from './homepage/homepage.component';
import { NavbarComponent } from './navbar/navbar.component';
import { WhenufreeRoutingModule } from './whenufree-routing/whenufree-routing.module';
import { SettimeComponent } from './settime/settime.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
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
  bootstrap: [AppComponent]
})
export class AppModule { }
