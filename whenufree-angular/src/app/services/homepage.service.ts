import { Injectable } from '@angular/core';
import {User} from '../domain/user';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { UserService } from './user.service';
import {UrlService} from './url.service';

@Injectable()
export class HomepageService {

    constructor(private url: UrlService,
	private userService: UserService,
	       private http: HttpClient) { }

  getCurrentUser(): Observable<User[]> {
      return this.http.get<User[]>(this.url.getUrl() + '/user', {withCredentials: true});
  }
}
