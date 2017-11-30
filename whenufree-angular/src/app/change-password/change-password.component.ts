/*
 * import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
	@Input() 

  constructor() { }

  ngOnInit() {
  }

}
*/
import {Component} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'example-app',
  template: `
     <input [formControl]="control">
      
     <p>Value: {{ control.value }}</p>
     <p>Validation status: {{ control.status }}</p>
     
     <button (click)="setValue()">Set value</button>
  `,
})
export class SimpleFormControl {
  control: FormControl = new FormControl('value', Validators.minLength(2));

  setValue() { this.control.setValue('new value'); }
}
