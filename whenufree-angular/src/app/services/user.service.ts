import { Injectable } from '@angular/core';
import { User } from '../domain/user'
import { Observable } from 'rxjs/Observable'
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService } from '../message.service';
import {UrlService} from './url.service';

@Injectable()
export class UserService {
	
	headers = new HttpHeaders()
		.set('X-Requested-With', 'XMLHttpRequest'); // to suppress 401 browser popup
	options = {headers: this.headers, withCredentials: true};


    constructor( private url: UrlService,
		 private http: HttpClient,
		 private messageService: MessageService) { }

	getUser(): Observable<User> {
	    return this.http.get<User>(this.url.getUrl() + '/user', this.options)
	}

	updateUser( user: User): Observable<User> {
	    return this.http.post<User>(this.url.getUrl() + '/updateuser', user, this.options);
	}

	deleteUser( user: User): Observable<User> {
		return this.http.post<User>(this.url.getUrl() + '/user', user, this.options);
	}
}
