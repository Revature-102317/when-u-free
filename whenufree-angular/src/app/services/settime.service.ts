import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {TimeSlot} from '../domain/timeSlot';
import {UrlService} from './url.service';

@Injectable()
export class SettimeService {

    constructor(private url: UrlService,
		private http: HttpClient) { }

  getTimes(): Observable<TimeSlot[]> {
      return this.http.get<TimeSlot[]>(this.url.getUrl() + '/timeslots', {withCredentials: true});
  }

  //Sets default times
  setDefaultTime(id: string): Observable<Object> {
      return this.http.post(this.url.getUrl() + '/setdefaulttime', {id}, {withCredentials: true});
  }

  //Submit button
  submitDefaultTime(submit: string): Observable<Object> {
      return this.http.post(this.url.getUrl() + '/setdefaulttime', {'submit': submit}, {withCredentials: true});
  }

  //gets the user default timeslots
  getUserDefaultTime(): Observable<TimeSlot[]> {
      return this.http.get<TimeSlot[]>(this.url.getUrl() + '/mydefaulttimes', {withCredentials: true});
  }

  //gets the user default timeslots
  getUserScheduledTime(): Observable<TimeSlot[]> {
      return this.http.get<TimeSlot[]>(this.url.getUrl() + '/myscheduledtimes', {withCredentials: true});
  }
}
