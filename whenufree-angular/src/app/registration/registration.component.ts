import { Component, OnInit } from '@angular/core';

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

  constructor() {
  }

  ngOnInit() {
  }

  onSubmit() {
    var registrationObj = {
      'email': this.email,
      'firstname': this.firstname,
      'lastname': this.lastname,
      'phone': this.phone,
      'password': this.password
    };

    console.log(JSON.stringify(registrationObj));
  }
}
