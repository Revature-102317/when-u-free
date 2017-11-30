import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {HomepageComponent} from '../homepage/homepage.component';
import {SettimeComponent} from '../settime/settime.component';
import {LoginComponent} from '../login/login.component';
import {SearchComponent} from '../search/search.component';
import {RegistrationComponent} from '../registration/registration.component';
import {FriendgroupsComponent} from '../friendgroups/friendgroups.component';
import {SettimescheduledComponent} from "../settimescheduled/settimescheduled.component";
import {LoadingpageComponent} from "../loadingpage/loadingpage.component";

const routes: Routes = [
    {path: 'homepage', component: HomepageComponent},
    {path: 'settime', component: SettimeComponent},
    {path: '', component: LoginComponent},
    {path: 'search', component: SearchComponent},
    {path: 'registration', component: RegistrationComponent},
    {path: 'friendgroups', component: FriendgroupsComponent},
    {path: 'settimescheduled', component: SettimescheduledComponent},
    {path: 'loadingpage', component: LoadingpageComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class WhenufreeRoutingModule { }
