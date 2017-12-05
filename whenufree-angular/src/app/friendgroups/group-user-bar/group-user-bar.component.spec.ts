import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupUserBarComponent } from './group-user-bar.component';

describe('GroupUserBarComponent', () => {
  let component: GroupUserBarComponent;
  let fixture: ComponentFixture<GroupUserBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupUserBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupUserBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
