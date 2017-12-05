import { TestBed, inject } from '@angular/core/testing';

import { GroupuserService } from './groupuser.service';

describe('GroupuserService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GroupuserService]
    });
  });

  it('should be created', inject([GroupuserService], (service: GroupuserService) => {
    expect(service).toBeTruthy();
  }));
});
