import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupPage1Component } from './group-page1.component';

describe('GroupPage1Component', () => {
  let component: GroupPage1Component;
  let fixture: ComponentFixture<GroupPage1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupPage1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupPage1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FriendgroupsComponent } from './friendgroups.component';

describe('FriendgroupsComponent', () => {
  let component: FriendgroupsComponent;
  let fixture: ComponentFixture<FriendgroupsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FriendgroupsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FriendgroupsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
