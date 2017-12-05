import { Component, OnInit, Output} from '@angular/core';
import { User } from '../domain/user';
import { UserService } from '../services/user.service';

@Component({
	selector: 'app-settings',
	templateUrl: './settings.component.html',
	styleUrls: ['./settings.component.css'],
})
export class SettingsComponent implements OnInit {
	user: User;
	editInfo: boolean = false;
	constructor( private userService: UserService ) { }

	ngOnInit(): void {
		this.getUser();
	}

	getUser(): void {
		this.userService.getUser().subscribe( user => this.user = user);
	}

	onEdit(): void {
		this.editInfo = true;
	}

	onCancel( clicked: boolean): void {
		clicked ? this.editInfo = false: this.editInfo = true;
	}
}
