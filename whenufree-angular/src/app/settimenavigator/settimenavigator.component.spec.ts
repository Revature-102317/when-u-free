import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SettimenavigatorComponent } from './settimenavigator.component';

describe('SettimenavigatorComponent', () => {
  let component: SettimenavigatorComponent;
  let fixture: ComponentFixture<SettimenavigatorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SettimenavigatorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SettimenavigatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
