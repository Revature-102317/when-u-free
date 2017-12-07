import { Injectable } from '@angular/core';
import { User } from '../domain/user'
import { Observable } from 'rxjs/Observable'
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService } from '../message.service';
import { UrlService } from './url.service';

@Injectable()
export class UserService {
	
	headers = new HttpHeaders()
		.set('X-Requested-With', 'XMLHttpRequest'); // to suppress 401 browser popup
	options = {headers: this.headers, withCredentials: true};

	constructor(
		private http: HttpClient,
		private messageService: MessageService,
		private url: UrlService) { }

	getUser(): Observable<User> {
	    return this.http.get<User>(this.url.getUrl() + '/user', this.options)
	}

	updateUser( user: User): Observable<User> {
		return this.http.post<User>(this.url.getUrl() + '/updateuser', user, this.options);
	}

	deleteUser( user: User): Observable<any> {
		return this.http.post<any>(this.url.getUrl() + '/deleteuser', user, this.options);
	}
}
