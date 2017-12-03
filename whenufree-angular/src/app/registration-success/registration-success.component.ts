import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-registration-success',
  templateUrl: './registration-success.component.html',
  styleUrls: ['./registration-success.component.css']
})
export class RegistrationSuccessComponent implements OnInit {

    constructor(private router: Router) { }

    ngOnInit() {
	setTimeout(
	    () => {
		this.router.navigate(['']);
	    }, 3000);
    }

}
