import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdminComponent }           from './admin.component';
import { AuthGuard }                from '../services/auth-guard.service';
import { AdminDashboardComponent }  from './admin-dashboard.component';
import { ManageGroupsComponent } from './manage-friendgroups.component';


const adminRoutes: Routes = [
  {
    path: 'admin',
    component: AdminComponent,
    children: [
		{
        path: '',
		canActivateChild: [ AuthGuard],
        children: [
          { path: 'groups', component: ManageGroupsComponent },
          //{ path: 'settings', component: ManageHeroesComponent },
          { path: '', component: AdminDashboardComponent }
        ]
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild( adminRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AdminRoutingModule { }
