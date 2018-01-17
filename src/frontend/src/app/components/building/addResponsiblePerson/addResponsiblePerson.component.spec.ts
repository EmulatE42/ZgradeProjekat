import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';


import { AddResponsiblePersonComponent } from './addResponsiblePerson.component';
import { ActivatedRouteStub } from '../../../testing/router-stubs';
import { AdminService } from "../../../services/admin.service";

describe('AddResponsiblePersonComponent', () => {
  let componentForAddResponsiblePerson: AddResponsiblePersonComponent;
  let fixture: ComponentFixture<AddResponsiblePersonComponent>;
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
      declarations: [ AddResponsiblePersonComponent ],
      providers:    [
        AdminService,
        { provide: Router, useValue: routerMock },
        { provide: ActivatedRoute, useValue: activatedRouteStub }
      ]
    });

    fixture = TestBed.createComponent(AddResponsiblePersonComponent);
    componentForAddResponsiblePerson = fixture.componentInstance;
    adminService = TestBed.get(AdminService);
    router = TestBed.get(Router);
  });

  it('should get all institutions', () => {
    fixture.whenStable()
      .then(() => {
        expect(adminService.getAllInstitutions).toHaveBeenCalled();
      });
  });

  it('should get all tenants', () => {
    componentForAddResponsiblePerson.getAllTenants();
    fixture.whenStable()
      .then(() => {
        expect(adminService.getAllTenants).toHaveBeenCalled();
      });
  });

  it('should add new responsible person', () => {
    componentForAddResponsiblePerson.add();
    fixture.whenStable()
      .then(() => {
        expect(adminService.addResponsiblePerson).toHaveBeenCalled();
      });
  });
});
