import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router'
import {HttpClient} from '@angular/common/http';

import {AuthenticationService} from '../services/authentication.service'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    username: string = '';
    password: string = '';
    errorMessage: string = '';

    
    constructor(private route: ActivatedRoute,
		private router: Router,
		private http: HttpClient,
		private authService: AuthenticationService){
    }

    ngOnInit() {
    }
    
    onSubmit(){
	this.authService.authenticate(this.username, this.password)
	    .subscribe(
		success =>{
		    this.router.navigate(['homepage']);
		},
		error=> {
		    this.errorMessage = "Wrong credentials, please try again."
		});
    }

}
