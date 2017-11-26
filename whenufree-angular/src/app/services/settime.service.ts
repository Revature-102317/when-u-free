import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {TimeSlot} from '../domain/timeSlot';

@Injectable()
export class SettimeService {

  constructor(private http: HttpClient) { }

  getTimes(): Observable<TimeSlot[]> {
    return this.http.get<TimeSlot[]>('http://localhost:8080/');
  }

}
