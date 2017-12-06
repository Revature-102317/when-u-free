import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupApplicationsComponent } from './group-applications.component';

describe('GroupApplicationsComponent', () => {
  let component: GroupApplicationsComponent;
  let fixture: ComponentFixture<GroupApplicationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupApplicationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupApplicationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
