import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  styleUrls: ['./app.component.css'],
  template: `
  <!--The content below is only a placeholder and can be replaced.-->
  <!-- <app-navbar></app-navbar> -->
  <router-outlet></router-outlet>
  `
  })
export class AppComponent {
  title = 'WhenUFree';
}
