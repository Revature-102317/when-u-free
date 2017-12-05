import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {FriendGroup} from '../domain/friendgroup';
import {UrlService} from './url.service';
import {User} from '../domain/user';
import {GroupFreeTime} from '../domain/groupFreeTime';

@Injectable()
export class GroupUserService {

/* COMMENTS

This Service is for the user and for the purpose of getting Friend Group data
As well as manipulating that data

*/
  constructor(private http: HttpClient, private url: UrlService) { }

  // gets the user friendgroups
  getFriendGroups(): Observable<FriendGroup[]> {
    return this.http.get<FriendGroup[]>(this.url.getUrl() + '/myfriendgroups', {withCredentials: true});
  }

  // posts the current friendgroup that was clicked on to the user
  postActiveFriendGroup(friendGroupId: string): Observable<FriendGroup> {
    return this.http.post<FriendGroup>(this.url.getUrl() + '/clickedfriendgroup', {friendGroupId}, {withCredentials: true});
  }

/*
  // gets the active friendgroup
  getActiveFriendGroup(): Observable<FriendGroup> {
    return this.http.get<FriendGroup> (this.url.getUrl() + '/friendgroup', {withCredentials: true});
  }
*/
  // gets the users of the active friendgroup
  getUsers(): Observable<User[]> {
    return this.http.get<User[]> (this.url.getUrl() + '/friendgroupusers', {withCredentials: true});
  }

  // gets the groupfreetimes
  getGroupFreeTimes(): Observable<GroupFreeTime[]> {
    return this.http.get<GroupFreeTime[]> (this.url.getUrl() + '/groupfreetimes', {withCredentials: true});
}
}
