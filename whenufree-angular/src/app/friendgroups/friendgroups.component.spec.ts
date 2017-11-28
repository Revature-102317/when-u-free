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
