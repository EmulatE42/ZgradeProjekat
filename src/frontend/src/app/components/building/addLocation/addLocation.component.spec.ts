import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';


import { AddLocationComponent } from './addLocation.component';
import { ActivatedRouteStub } from '../../../testing/router-stubs';
import { AdminService } from "../../../services/admin.service";

describe('AddLocationComponent', () => {
  let componentForAddComeponent: AddLocationComponent;
  let fixture: ComponentFixture<AddLocationComponent>;
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
      declarations: [ AddLocationComponent ],
      providers:    [
        AdminService,
        { provide: Router, useValue: routerMock },
        { provide: ActivatedRoute, useValue: activatedRouteStub }
      ]
    });

    fixture = TestBed.createComponent(AddLocationComponent);
    componentForAddComeponent = fixture.componentInstance;
    adminService = TestBed.get(AdminService);
    router = TestBed.get(Router);
  });

  it('should add new location of building', () => {
    fixture.whenStable()
      .then(() => {
        componentForAddComeponent.addLocation();
        expect(adminService.addLocation).toHaveBeenCalled();
      });
  });

  it('should navigate to display building', () => {
    fixture.whenStable()
      .then(() => {
        componentForAddComeponent.goToDisplayBuilding();
        expect(router.navigate).toHaveBeenCalledWith(
          ['/displayBuilding',componentForAddComeponent.buildingId]);
      });
  });
});
