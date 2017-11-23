import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router'
import {HttpClient} from '@angular/common/http';


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
		private http: HttpClient){
    }

    ngOnInit() {
    }
    
    onSubmit(){
	var loginObj = {'username': this.username, 'password': this.password};
    }

}
