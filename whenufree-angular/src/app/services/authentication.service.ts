import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {User} from '../domain/user';
import {UserService} from './user.service'

@Injectable()
export class AuthenticationService {

    constructor(
		private http: HttpClient,
		private userService: UserService) { }

    authenticate(username: string, password: string): Observable<object>{
        var loginUrl = 'http://localhost:8080/login';
        let headers = new HttpHeaders({
          'Authorization': 'Basic ' + btoa(username + ':' + password),
          'X-Requested-With': 'XMLHttpRequest' // to suppress 401 browser popup
        });

	let options = {headers: headers, withCredentials: true}

	return this.http.post(loginUrl, {}, options)
    }

    getUser(): Observable<User> {
<<<<<<< HEAD
		return this.userService.getUser();
=======
      let userUrl = 'http://localhost:8080/user';
      let headers = new HttpHeaders()
        .set('X-Requested-With', 'XMLHttpRequest'); // to suppress 401 browser popup

      let options = {headers: headers, withCredentials: true};
      return this.http.get<User>(userUrl, options);
>>>>>>> 4495ff3c46a6ead626e7529cacc9654f7c1eca86
    }

    logout() {
      let logOutUrl = 'http://localhost:8080/logout';
      let headers = new HttpHeaders()
        .set('X-Requested-With', 'XMLHttpRequest'); // to suppress 401 browser popup
      let options = {headers: headers, withCredentials: true};
      return this.http.post(logOutUrl, {}, options);
    }


}
