import { Injectable } from '@angular/core';
import {User} from '../domain/user';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { UserService } from './user.service';

@Injectable()
export class HomepageService {

  constructor( private userService: UserService,
			  private http: HttpClient) { }

  getCurrentUser(): Observable<User[]> {
    return this.http.get<User[]>('http://localhost:8080/user', {withCredentials: true});
  }
}
