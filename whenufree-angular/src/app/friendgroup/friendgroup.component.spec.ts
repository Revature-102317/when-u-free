import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FriendgroupComponent } from './friendgroup.component';

describe('FriendgroupComponent', () => {
  let component: FriendgroupComponent;
  let fixture: ComponentFixture<FriendgroupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FriendgroupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FriendgroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
