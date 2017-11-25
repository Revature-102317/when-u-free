import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {HomepageComponent} from '../homepage/homepage.component';
import {SettimeComponent} from '../settime/settime.component';
<<<<<<< HEAD

const routes: Routes = [
  {path: 'homepage', component: HomepageComponent},
  {path: 'settime', component: SettimeComponent}
=======
import {LoginComponent} from '../login/login.component';
import {SearchComponent} from '../search/search.component';
import {RegistrationComponent} from '../registration/registration.component';

const routes: Routes = [
    {path: 'homepage', component: HomepageComponent},
    {path: 'settime', component: SettimeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'search', component: SearchComponent},
    {path: 'registration', component: RegistrationComponent}
>>>>>>> 78cc519364ce84a24e404c29a15819d0438042fa
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class WhenufreeRoutingModule { }
