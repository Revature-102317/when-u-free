    import { NgModule }             from '@angular/core';
    import { RouterModule, Routes } from '@angular/router';
    import { AuthGuard }            from '../services/auth-guard.service';
    import { LoginComponent }       from './login.component';
	import { AuthenticationService } from '../services/authentication.service';
//	import { AuthService } from '../';
     
    const loginRoutes: Routes = [
      { path: 'login', component: LoginComponent }
    ];
     
    @NgModule({
      imports: [
        RouterModule.forChild(loginRoutes)
      ],
      exports: [
        RouterModule
      ],
      providers: [
        AuthGuard,
        AuthenticationService
      ]
    })
    export class LoginRoutingModule {}
