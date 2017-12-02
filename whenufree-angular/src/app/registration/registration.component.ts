import { Component, OnInit } from '@angular/core';
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

    constructor(private regService: RegistrationService) {
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
	    password: this.password,
	    friendsList: null,
	    connections: null
	}

	console.log(JSON.stringify(u));
	
	this.regService.registerUser(u).subscribe(
	    data => {
		console.log("success");
	    },
	    error => {
		this.errorMessage = "registration not successful, please try again";
	    }
	);
    }
}
