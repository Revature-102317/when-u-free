import { TestBed, inject } from '@angular/core/testing';

import { SettimeService } from './settime.service';

describe('SettimeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SettimeService]
    });
  });

  it('should be created', inject([SettimeService], (service: SettimeService) => {
    expect(service).toBeTruthy();
  }));
});
