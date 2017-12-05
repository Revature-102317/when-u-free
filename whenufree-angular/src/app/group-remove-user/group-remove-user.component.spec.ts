import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupRemoveUserComponent } from './group-remove-user.component';

describe('GroupRemoveUserComponent', () => {
  let component: GroupRemoveUserComponent;
  let fixture: ComponentFixture<GroupRemoveUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupRemoveUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupRemoveUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
