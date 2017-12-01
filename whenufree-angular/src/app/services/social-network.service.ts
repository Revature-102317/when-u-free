import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

import {UrlService} from './url.service';

import {Named} from '../domain/named';

@Injectable()
export class SocialNetworkService {

    constructor(private http: HttpClient,
		private urlService: UrlService) { }

    joinGroup(n: Named): Observable<any>{
	let url = this.urlService.getUrl() + "/joingroup";
	let headers = new HttpHeaders()
            .set('X-Requested-With', 'XMLHttpRequest'); // to suppress 401 browser popup
	let options = {headers: headers, withCredentials: true};

	return this.http.post(url, n, options);
    }

    addFriend(n: Named): Observable<any>{
	let url = this.urlService.getUrl() + "/addfriend";
	let headers = new HttpHeaders()
            .set('X-Requested-With', 'XMLHttpRequest'); // to suppress 401 browser popup
	let options = {headers: headers, withCredentials: true};

	return this.http.post(url, n, options);
    }

}
