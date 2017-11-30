import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SettimescheduledComponent } from './settimescheduled.component';

describe('SettimescheduledComponent', () => {
  let component: SettimescheduledComponent;
  let fixture: ComponentFixture<SettimescheduledComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SettimescheduledComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SettimescheduledComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
