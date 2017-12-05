import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Friendgroup} from '../domain/friendgroup';
import {UrlService} from './url.service';
import {User} from '../domain/user';
import {GroupFreeTime} from '../domain/groupFreeTime';
import {Named} from '../domain/named';

@Injectable()
export class GroupuserService {

/* COMMENTS

This Service is for the user and for the purpose of getting Friend Group data
As well as manipulating that data

*/
  constructor(private http: HttpClient, private url: UrlService) { }

  // gets the user friendgroups
  getFriendGroups(): Observable<Friendgroup[]> {
    return this.http.get<Friendgroup[]>(this.url.getUrl() + '/myfriendgroups', {withCredentials: true});
  }

  // posts the current friendgroup that was clicked on to the user
  postActiveFriendGroup(friendgroup: number): Observable<Object> {
    return this.http.post<string>(this.url.getUrl() + '/clickedfriendgroup', {friendgroup}, {withCredentials: true});
  }

  // gets the active friendgroup
  getActiveFriendGroup(): Observable<Friendgroup> {
    return this.http.get<Friendgroup> (this.url.getUrl() + '/friendgroup', {withCredentials: true});
  }

  // gets the users of the active friendgroup
  getUsers(): Observable<User[]> {
    return this.http.get<User[]> (this.url.getUrl() + '/friendgroupusers', {withCredentials: true});
  }

  // gets the groupfreetimes
  getGroupFreeTimes(): Observable<GroupFreeTime[]> {
    return this.http.get<GroupFreeTime[]> (this.url.getUrl() + '/groupfreetimes', {withCredentials: true});
}
  // gets the groupfreetimes in a list of cool strings
  getGroupFreeTimesBetter(): Observable<Named[]> {
    return this.http.get<Named[]> (this.url.getUrl() + '/groupfreetimesbetter', {withCredentials: true});
  }
}
