import { Injectable } from '@angular/core';
import { User } from '../domain/user'
import { Observable } from 'rxjs/Observable'
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable()
export class UserService {

	constructor( private http: HttpClient) { }

	getUser(): Observable<User> {
		let userUrl = 'http://localhost:8080/user';
		let headers = new HttpHeaders()
		.set('X-Requested-With', 'XMLHttpRequest'); // to suppress 401 browser popup

		let options = {headers: headers, withCredentials: true};
		return this.http.get<User>(userUrl, options);
	}

	/*
	updateUser(): void {
		let userUrl = 'http://localhost:8085/
	}
	*/

   /*
	* deleteUser(): void {
	* }
	*/

}
