import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {TimeSlot} from '../domain/timeSlot';

@Injectable()
export class SettimeService {

  constructor(private http: HttpClient) { }

  getTimes(): Observable<TimeSlot[]> {
    return this.http.get<TimeSlot[]>('http://localhost:8080/timeslots', {withCredentials: true});
  }

  //Sets default times
  setDefaultTime(id: string): Observable<Object> {
    return this.http.post('http://localhost:8080/setdefaulttime', {id}, {withCredentials: true});
  }

  //Submit button
  submitDefaultTime(submit: string): Observable<Object> {
    return this.http.post('http://localhost:8080/setdefaulttime', {'submit': submit}, {withCredentials: true});
  }

  //gets the user default timeslots
  getUserDefaultTime(): Observable<TimeSlot[]> {
    return this.http.get<TimeSlot[]>('http://localhost:8080/mydefaulttimes', {withCredentials: true});
  }

  //gets the user default timeslots
  getUserScheduledTime(): Observable<TimeSlot[]> {
    return this.http.get<TimeSlot[]>('http://localhost:8080/myscheduledtimes', {withCredentials: true});
  }
}
