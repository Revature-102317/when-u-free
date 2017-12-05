import { TestBed, inject } from '@angular/core/testing';

import { SocialNetworkService } from './social-network.service';

describe('SocialNetworkService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SocialNetworkService]
    });
  });

  it('should be created', inject([SocialNetworkService], (service: SocialNetworkService) => {
    expect(service).toBeTruthy();
  }));
});
