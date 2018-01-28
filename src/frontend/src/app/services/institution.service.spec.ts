import { TestBed, inject } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';

import { InstitutionService } from './institution.service';

describe('InstitutionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule,
        HttpModule
      ],
      providers: [InstitutionService]
    });
  });

  it('should be created', inject([InstitutionService], (service: InstitutionService) => {
    expect(service).toBeTruthy();
  }));
});
