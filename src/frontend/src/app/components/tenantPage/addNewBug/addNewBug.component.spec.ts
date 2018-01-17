import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';


import { AddNewBugComponent } from './addNewBug.component';
import { AdminService } from "../../../services/admin.service";
import { ActivatedRouteStub } from '../../../testing/router-stubs';
import { TenantService } from '../../../services/tenant.service';

describe('AddNewBugComponent', () => {
  let componentForAddNewBug: AddNewBugComponent;
  let fixture: ComponentFixture<AddNewBugComponent>;
  let adminService: any;
  let tenantService: any;
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
      declarations: [ AddNewBugComponent ],
      providers:    [
        AdminService,
        TenantService,
        { provide: Router, useValue: routerMock },
        { provide: ActivatedRoute, useValue: activatedRouteStub }
      ]
    });

    fixture = TestBed.createComponent(AddNewBugComponent);
    componentForAddNewBug = fixture.componentInstance;
    adminService = TestBed.get(AdminService);
    router = TestBed.get(Router);
    tenantService = TestBed.get(TenantService);
  });

  it('should get responsible persons by location id', () => {
    fixture.whenStable()
      .then(() => {
        expect(adminService.getAllResponsiblePersonsByLocationId).toHaveBeenCalled();
      });
  });

  it('should add new bug', () => {
    componentForAddNewBug.add();
    fixture.whenStable()
      .then(() => {
        expect(tenantService.addNewBug).toHaveBeenCalled();
      });
  });


});
