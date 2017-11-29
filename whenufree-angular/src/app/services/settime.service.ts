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

  getTime(): Observable<TimeSlot> {
    return this.http.get<TimeSlot>('http://localhost:8080/timeslot/1', {withCredentials: true});
  }

  setDefaultTime(id: string): Observable<Object> {
    return this.http.post('http://localhost:8080/setdefaulttime', {'id': id}, {withCredentials: true});
  }

  submitDefaultTime(submit: string): Observable<Object> {
    return this.http.post('http://localhost:8080/setdefaulttime', {'submit': submit}, {withCredentials: true});
  }
}
