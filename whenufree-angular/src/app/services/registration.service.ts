import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

import {User} from '../domain/user';
import {UrlService} from './url.service'


@Injectable()
export class RegistrationService {

    constructor(private urlService: UrlService,
		private http: HttpClient) { }

    registerUser(u: User): Observable<any>{
	let url = this.urlService.getUrl() + '/register';
	let options = {withCredentials: true};
	
	return this.http.post(url, u, options);
    }
}
