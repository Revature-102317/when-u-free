import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupPage3Component } from './group-page3.component';

describe('GroupPage3Component', () => {
  let component: GroupPage3Component;
  let fixture: ComponentFixture<GroupPage3Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupPage3Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupPage3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
