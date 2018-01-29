import { TestBed, inject } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';

import { FirmService } from './firm.service';

describe('FirmService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule,
        HttpModule
      ],
      providers: [FirmService]
    });
  });

  it('should be created', inject([FirmService], (service: FirmService) => {
    expect(service).toBeTruthy();
  }));
});
