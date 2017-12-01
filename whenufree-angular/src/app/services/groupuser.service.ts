import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Friendgroup} from '../domain/friendgroup'

@Injectable()
export class GroupuserService {

/* COMMENTS

This Service is for the user and for the purpose of getting Friend Group data
As well as manipulating that data

*/
  constructor(private http: HttpClient) { }

  //gets the user friendgroups
  getFriendGroups(): Observable<Friendgroup[]> {
    return this.http.get<Friendgroup[]>('http://localhost:8080/myfriendgroups', {withCredentials: true});
  }
}
