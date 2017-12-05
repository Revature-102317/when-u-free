import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupRightBarComponent } from './group-right-bar.component';

describe('GroupRightBarComponent', () => {
  let component: GroupRightBarComponent;
  let fixture: ComponentFixture<GroupRightBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupRightBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupRightBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
