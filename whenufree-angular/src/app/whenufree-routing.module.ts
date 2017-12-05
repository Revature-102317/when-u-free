import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {HomepageComponent} from './homepage/homepage.component';
import {SettimeComponent} from './settime/settime.component';
import {LoginComponent} from './login/login.component';
import {SearchComponent} from './search/search.component';
import {RegistrationComponent} from './registration/registration.component';
import {SettimescheduledComponent} from './settimescheduled/settimescheduled.component';
import {LoadingpageComponent} from './loadingpage/loadingpage.component';
import {RegistrationSuccessComponent} from './registration-success/registration-success.component';
import { PageNotFoundComponent } from './page-not-found.component';

const routes: Routes = [
	{path: 'login', component: LoginComponent},
    {path: 'homepage', component: HomepageComponent},
    {path: 'settime', component: SettimeComponent},
    {path: 'search', component: SearchComponent},
    {path: 'registration', component: RegistrationComponent},
    {path: 'settimescheduled', component: SettimescheduledComponent},
    {path: 'loadingpage', component: LoadingpageComponent},
    {path: 'registrationsuccess', component: RegistrationSuccessComponent},
    {path: '', redirectTo: '/login', pathMatch: 'full'},
	{path: '**', component: PageNotFoundComponent }
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class WhenufreeRoutingModule { }
