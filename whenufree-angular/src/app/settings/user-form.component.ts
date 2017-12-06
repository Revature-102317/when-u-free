import { Component, EventEmitter, OnInit, Input, Output, OnDestroy} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { User } from '../domain/user';
import { UserService } from '../services/user.service';
import { AuthenticationService } from '../services/authentication.service';
import { MessageService } from '../message.service';
import { Router } from '@angular/router';

@Component({
	selector: 'app-user-info',
	templateUrl: './user-info.component.html',
	styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {
	@Input() user: User;
	constructor( protected userService: UserService) { }

	ngOnInit(): void {
	}
}

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent extends UserInfoComponent implements OnDestroy {
	@Output() onCancel = new EventEmitter<boolean>();
	userForm: FormGroup;
	extendForm: boolean = false;
	youveBeenWarned: boolean = false;

	constructor( private formBuilder: FormBuilder,
				protected userService: UserService,
			   private authService: AuthenticationService,
			   private messageService: MessageService,
			   private router: Router) {
		super( userService);
		this.createForm();
	}

	ngOnDestroy() {
		this.messageService.clear();
	}

	createForm() {
		this.userForm = this.formBuilder.group({
			user: this.formBuilder.group({
				firstname: '', // <--- the FormControl called "name"
				lastname: '',
				email: '',
				phone: ''
			}),
			passwords: this.formBuilder.group({
				currentPassword: '',
				newPassword: '',
				confirmPassword: ''
			})
		});
	}

	onSave(): void {
		if( this.extendForm && this.userForm.get('passwords.newPassword').value === this.userForm.get('passwords.confirmPassword').value) 
			this.user.newPassword = this.userForm.get('passwords.newPassword').value;
		this.userService.updateUser( this.user).subscribe(
			success => {
				this.router.navigate(['homepage']);
			},
			error => {
				alert(" Passwords don't match");
			}
		);
	}

	onDelete(): void {
		console.log(this.youveBeenWarned);
		if( this.youveBeenWarned === true) {
			this.userService.deleteUser(this.user).subscribe(
				success => {
					this.router.navigate(['']);
				},
				error => {
					alert(" Didn't type correct Password.");
				}
			);
		} else {
			this.youveBeenWarned = true;
			alert( "Correctly fill out form and click again if you are sure you want to delete your account.  You've been warned....!");
		}
		
	}

	cancelEdit( clicked: boolean) {
		this.onCancel.emit( clicked);
		this.extendForm = false;
	}

	onChangePassword(): void {
		this.extendForm = true;
	}
}
