import { Component, OnInit, ViewChild, Input} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { User } from '../domain/user';
import { UserService } from '../services/user.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
	selector: 'app-user-info',
	templateUrl: './user-info.component.html',
	styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {
	user: User;
	constructor( protected userService: UserService) { }

	ngOnInit(): void {
		this.getUser();
	}

	getUser(): void {
		this.userService.getUser().subscribe( user => this.user = user);
	}
}

@Component({
  selector: 'app-default-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent extends UserInfoComponent {
	@Input('editInfo') cancelEdit: boolean;
	userFormTemplate: User;
	defaultUserForm: FormGroup;
	extendForm: boolean = false;

	constructor( private formBuilder: FormBuilder,
				protected userService: UserService ) {
		super( userService);
		this.createForm();
	}

	createForm() {
		this.defaultUserForm = this.formBuilder.group({
			user: this.formBuilder.group({
				firstname: '', // <--- the FormControl called "name"
				lastname: '',
				email: '',
				phone: ''
			}),
			passwords: this.formBuilder.group({
				currentPassword: ' ',
				newPassword: '',
				confirmPassword: ''
			})
		});
	}

	onSave(): void {
		//this.userService.updateUser();
	}

	onDelete(): void {
		//this.userService.deleteUser();
	}

	onCancel():void {
		this.cancelEdit = false;
	}

	onChangePassword(): void {
		this.extendForm = true;
	}
}
