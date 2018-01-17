import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';


import { LocationComponent } from './location.component';
import { AdminService } from "../../services/admin.service";
import { ActivatedRouteStub } from '../../testing/router-stubs';
import { TenantDTO, LocationDTO } from "../../models";

describe('LocationComponent', () => {
  let componentForLocation: LocationComponent;
  let fixture: ComponentFixture<LocationComponent>;
  let adminService: any;
  let router: any;

  beforeEach(() => {
    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    let activatedRouteStub: ActivatedRouteStub = new ActivatedRouteStub();
    activatedRouteStub.testParams = { id: 1 }

    TestBed.configureTestingModule({
      imports: [
        HttpModule,
        HttpClientModule,
        FormsModule
      ],
      declarations: [ LocationComponent ],
      providers:    [
        AdminService,
        { provide: Router, useValue: routerMock },
        { provide: ActivatedRoute, useValue: activatedRouteStub }
      ]
    });

    fixture = TestBed.createComponent(LocationComponent);
    componentForLocation = fixture.componentInstance;
    adminService = TestBed.get(AdminService);
    router = TestBed.get(Router);
  });

  it('should get all tenants', () => {
    fixture.whenStable()
      .then(() => {
        expect(adminService.getAllTenants).toHaveBeenCalled();
      });
  });

  it('should connect tenant and apartment', () => {
    componentForLocation.selectedTenant = new TenantDTO(1,"pero","pero","pero",false);
    fixture.whenStable()
      .then(() => {
      componentForLocation.connect();
      fixture.whenStable()
        .then(() => {
          expect(adminService.connectTenantAndApartment).toHaveBeenCalled();
        });
      });
  });

});
