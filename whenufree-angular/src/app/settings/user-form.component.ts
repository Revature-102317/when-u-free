import { Component, EventEmitter, OnInit, Input, Output, OnDestroy} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { User } from '../domain/user';
import { UserService } from '../services/user.service';
import { AuthenticationService } from '../services/authentication.service';
import { MessageService } from '../message.service';

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

	constructor( private formBuilder: FormBuilder,
				protected userService: UserService,
			   private authService: AuthenticationService,
			   private messageService: MessageService) {
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
		if( this.extendForm && this.userForm.get('passwords').get('newPassword').value == this.userForm.get('passwords').get('confirmPassword').value) 
			this.user.newPassword = this.userForm.get('newPassword').value;
		else
			console.log('new passwords don\'t match');
		if( this.userForm.get('passwords').get('currentPassword').value == this.user.currentPassword) {
			this.userService.updateUser( this.user).subscribe(
				success => this.messageService.add(" It worked!"),
					error => this.messageService.add(" Nope")
			);
		}
	}

	onDelete(): void {
		this.userService.deleteUser(this.user).subscribe(
			success => {
				this.messageService.add(" It worked!");
			},
			error => {
				this.messageService.add(" Nope");
			});
	}

	cancelEdit( clicked: boolean) {
		this.onCancel.emit( clicked);
		this.extendForm = false;
	}

	onChangePassword(): void {
		this.extendForm = true;
	}
}
