import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {HomepageComponent} from '../homepage/homepage.component';
import {SettimeComponent} from '../settime/settime.component';
import {LoginComponent} from '../login/login.component';
import {SearchComponent} from '../search/search.component'

const routes: Routes = [
    {path: 'homepage', component: HomepageComponent},
    {path: 'settime', component: SettimeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'search', component: SearchComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class WhenufreeRoutingModule { }
