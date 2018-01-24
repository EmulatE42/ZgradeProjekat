import { async, fakeAsync,tick, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { TenantPageComponent } from './tenantPage.component';
import { TenantService } from '../../services/tenant.service';
import {Observable} from 'rxjs/Observable';

class MyServiceMock {
  getApartmentsOfTenant() { return Promise.resolve(); }
}

describe('TenantPageComponent', () => {
  let componentForTenantPage: TenantPageComponent;
  let fixture: ComponentFixture<TenantPageComponent>;
  let tenantService: any;

  beforeEach(async(() => {
    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    let tenantServiceMock = {
      getApartmentsOfTenant: jasmine.createSpy('getApartmentsOfTenant')
        .and.returnValue(Promise.resolve())
    };

    TestBed.configureTestingModule({
      imports: [
        HttpModule,
        HttpClientModule,
        FormsModule
      ],
      declarations: [ TenantPageComponent ],
      providers:    [
        TenantService,
        { provide: Router, useValue: routerMock }
      ]
    }).compileComponents().then(() => {
      fixture = TestBed.createComponent(TenantPageComponent);
      componentForTenantPage = fixture.componentInstance;
      tenantService = TestBed.get(TenantService);
      //tenantService = fixture.debugElement.injector.get(TenantService);
    });
  }));

  /*
  beforeEach(() => {
    fixture = TestBed.createComponent(TenantPageComponent);
    componentForTenantPage = fixture.componentInstance;
    tenantService = TestBed.get(TenantService);
  });
  */

  /*
  it('should get apartments of tenant', () => {
    fixture.whenStable()
      .then(() => {
        expect(tenantService.getApartmentsOfTenant).toHaveBeenCalled();
      });

  });
  */

});
