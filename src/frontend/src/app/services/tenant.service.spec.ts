import { TestBed, inject } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';

import { TenantService } from './tenant.service';

describe('TenantService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule,
        HttpModule
      ],
      providers: [TenantService]
    });
  });

  it('should be created', inject([TenantService], (service: TenantService) => {
    expect(service).toBeTruthy();
  }));
});
