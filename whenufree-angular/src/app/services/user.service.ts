import { Injectable } from '@angular/core';
import { User } from '../domain/user'
import { Observable } from 'rxjs/Observable'
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { MessageService } from '../message.service';


@Injectable()
export class UserService {
	userUrl = 'http://localhost:8080/user';
	headers = new HttpHeaders()
		.set('X-Requested-With', 'XMLHttpRequest'); // to suppress 401 browser popup
	options = {headers: this.headers, withCredentials: true};


	constructor( private http: HttpClient,
			   private authService: AuthenticationService,
			   private messageService: MessageService) { }

	getUser(): Observable<User> {
		return this.http.get<User>(this.userUrl, this.options);
	}

	updateUser( user: User): void {
		this.authService.authenticate( user.firstname, user.password)
			.subscribe(
				success => {
					return this.http.put<User>(this.userUrl, {user},
											   this.options);
				},
				error => {
					this.messageService.add( "Error sending info over network");
				});
	}

	updateUserNewPassword( user: User, newPassword: string): void {
		this.authService.authenticate( user.firstname, newPassword)
			.subscribe(
				success => {
					return this.http.put<User>(this.userUrl, {user, newPassword},
											   this.options);
				},
				error => {
					this.messageService.add( "Error sending info over network");
				});
	}
}
