import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupsettingsComponent } from './groupsettings.component';

describe('GroupsettingsComponent', () => {
  let component: GroupsettingsComponent;
  let fixture: ComponentFixture<GroupsettingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupsettingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupsettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
