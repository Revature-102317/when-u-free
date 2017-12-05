import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {RegistrationService} from '../services/registration.service';
import {User} from '../domain/user';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  email: string;
  firstname: string;
  lastname: string;
  phone: string;
  password: string;

  errorMessage: string;

    constructor(private regService: RegistrationService,
		private router: Router) {
    }
    
    ngOnInit() {
    }

    onSubmit() {
	var u: User = {
	    userId: null,
	    email: this.email,
	    firstname: this.firstname,
	    lastname: this.lastname,
	    phone: this.phone,
	    currentPassword: this.password,
		newPassword: null,
	    friendsList: null,
	    connections: null
	}

	console.log(JSON.stringify(u));
	
	this.regService.registerUser(u).subscribe(
	    data => {
		this.router.navigate(['registrationsuccess']);
	    },
	    error => {
		this.errorMessage = "registration not successful, please try again";
	    }
	);
    }
}
