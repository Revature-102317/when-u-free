import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SettimeComponent } from './settime.component';

describe('SettimeComponent', () => {
  let component: SettimeComponent;
  let fixture: ComponentFixture<SettimeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SettimeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SettimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
