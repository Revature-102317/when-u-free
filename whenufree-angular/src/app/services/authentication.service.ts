import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {User} from '../domain/user';

@Injectable()
export class AuthenticationService {

    constructor(
		private http: HttpClient) { }

    authenticate(username: string, password: string): Observable<object>{
        var loginUrl = 'http://localhost:8080/login';
        let headers = new HttpHeaders({
          'Authorization': 'Basic ' + btoa(username + ':' + password),
          'X-Requested-With': 'XMLHttpRequest' // to suppress 401 browser popup
        });

	let options = {headers: headers, withCredentials: true};

	return this.http.post(loginUrl, {}, options);
    }

    logout(): Observable<any> {
      let logOutUrl = 'http://localhost:8080/logout';
      let headers = new HttpHeaders()
        .set('X-Requested-With', 'XMLHttpRequest'); // to suppress 401 browser popup
      let options = {headers: headers, withCredentials: true};
      return this.http.post(logOutUrl, {}, options);
    }


}
