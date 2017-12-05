import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';


import {UrlService} from './url.service';

@Injectable()
export class SchedulingService {

     headers = new HttpHeaders()
        .set('X-Requested-With', 'XMLHttpRequest'); // to suppress 401 browser popup
    
    constructor(private urlService: UrlService,
		private http: HttpClient) { }

    scheduleEvent(obj): Observable<any>{
	let url = this.urlService.getUrl() + "/scheduleevent";
	let options = {headers: this.headers, withCredentials: true};

	return this.http.post(url, obj, options);
    }
}
