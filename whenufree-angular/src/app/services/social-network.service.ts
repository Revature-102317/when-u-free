import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

import {UrlService} from './url.service';

import {Named} from '../domain/named';

@Injectable()
export class SocialNetworkService {

    headers = new HttpHeaders()
        .set('X-Requested-With', 'XMLHttpRequest'); // to suppress 401 browser popup
    constructor(private http: HttpClient,
		private urlService: UrlService) { }

    joinGroup(n: Named): Observable<any>{
	let url = this.urlService.getUrl() + "/joingroup";
	let options = {headers: this.headers, withCredentials: true};

	return this.http.post(url, n, options);
    }

    acceptGroup(n: Named): Observable<any>{
	let url = this.urlService.getUrl() + "/acceptgroup";
	let options = {headers: this.headers, withCredentials: true};

	return this.http.post(url, n, options);
    }
    leaveGroup(n: Named): Observable<any>{
	let url = this.urlService.getUrl() + "/leavegroup";
	let options = {headers: this.headers, withCredentials: true};

	return this.http.post(url, n, options);
    }

    addFriend(n: Named): Observable<any>{
	let url = this.urlService.getUrl() + "/addfriend";
	let options = {headers: this.headers, withCredentials: true};

	return this.http.post(url, n, options);
    }

    acceptFriend(n: Named): Observable<any>{
	let url = this.urlService.getUrl() + "/acceptfriend";
	let options = {headers: this.headers, withCredentials: true};

	return this.http.post(url, n, options);
    }

    removeFriend(n: Named): Observable<any>{
	let url = this.urlService.getUrl() + "/removefriend";
	let options = {headers: this.headers, withCredentials: true};

	return this.http.post(url, n, options);
    }

    inviteUser(n: Named, id: number): Observable<any>{
	let url = this.urlService.getUrl() + "/inviteuser" + "/" + id;
	let options = {headers: this.headers, withCredentials: true};

	return this.http.post(url, n, options);
    }

    removeUser(n: Named, id: number): Observable<any>{
	let url = this.urlService.getUrl() + "/removeuser" + "/" + id;
	let options = {headers: this.headers, withCredentials: true};

	return this.http.post(url, n, options);
    }

    approveUser(n: Named, id: number): Observable<any>{
	let url = this.urlService.getUrl() + "/approveuser" + "/" + id;
	let options = {headers: this.headers, withCredentials: true};

	return this.http.post(url, n, options);
    }
    
    
    
    getUser(id: number): Observable<Named>{
	let url = this.urlService.getUrl() + '/getuser' + '/' + id;
	let options = {headers: this.headers, withCredentials: true};
	return this.http.get<Named>(url, options);
    }

    getFriendGroup(id: number): Observable<Named>{
	let url = this.urlService.getUrl() + '/getfriendgroup' + '/' + id;
	let options = {headers: this.headers, withCredentials: true};
	return this.http.get<Named>(url, options);
    }

    getApplied(id: number): Observable<Named[]>{
	let url = this.urlService.getUrl() + '/getapplied' + '/' + id;
	let options = {headers: this.headers, withCredentials: true};
	return this.http.get<Named[]>(url, options);
    }

}
