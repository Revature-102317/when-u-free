import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {User} from '../domain/user';
import {UrlService} from './url.service';


@Injectable()
export class AuthenticationService {

    constructor(private urlService: UrlService,
		private http: HttpClient) { }

    authenticate(username: string, password: string): Observable<object>{
        var loginUrl = this.urlService.getUrl() + '/login';
        let headers = new HttpHeaders({
          'Authorization': 'Basic ' + btoa(username + ':' + password),
          'X-Requested-With': 'XMLHttpRequest' // to suppress 401 browser popup
        });

	let options = {headers: headers, withCredentials: true};

	return this.http.post(loginUrl, {}, options);
    }

    logout(): Observable<any> {
	let logOutUrl = this.urlService.getUrl() + '/logout';
	let headers = new HttpHeaders()
            .set('X-Requested-With', 'XMLHttpRequest'); // to suppress 401 browser popup
	let options = {headers: headers, withCredentials: true};
	return this.http.post(logOutUrl, {}, options);
    }


}
