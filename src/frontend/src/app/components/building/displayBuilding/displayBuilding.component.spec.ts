import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { DisplayBuildingComponent } from './displayBuilding.component';
import { AdminService } from "../../../services/admin.service";
import { ActivatedRouteStub } from '../../../testing/router-stubs';

describe('DisplayBuildingComponent', () => {
  let componentForDisplayBuilding: DisplayBuildingComponent;
  let fixture: ComponentFixture<DisplayBuildingComponent>;
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
      declarations: [ DisplayBuildingComponent ],
      providers:    [
        AdminService,
        { provide: Router, useValue: routerMock },
        { provide: ActivatedRoute, useValue: activatedRouteStub }
      ]
    });

    fixture = TestBed.createComponent(DisplayBuildingComponent);
    componentForDisplayBuilding = fixture.componentInstance;
    adminService = TestBed.get(AdminService);
    router = TestBed.get(Router);
  });

  it('should get building by id', () => {
    componentForDisplayBuilding.ngOnInit();
    fixture.whenStable()
      .then(() => {
        fixture.detectChanges();
        expect(adminService.getBuildingById).toHaveBeenCalled();
      });
  });

  it('should delete building', () => {
    componentForDisplayBuilding.ngOnInit();
    fixture.whenStable()
      .then(() => {
      componentForDisplayBuilding.deleteLocation(1,1);
      fixture.whenStable()
        .then(() => {
          fixture.detectChanges();
          expect(adminService.deleteLocation).toHaveBeenCalled();
        });
      });
  });

  it('should navigate to location', () => {
    fixture.whenStable()
      .then(() => {
        componentForDisplayBuilding.goToLocation(1);
        expect(router.navigate).toHaveBeenCalledWith(
          ['/location',1]);
      });
  });

  it('should navigate to add location', () => {
    fixture.whenStable()
      .then(() => {
        componentForDisplayBuilding.goToAddLocation(1);
        expect(router.navigate).toHaveBeenCalledWith(
          ['/addLocation',1]);
      });
  });

  it('should navigate to add new responsible person', () => {
    fixture.whenStable()
      .then(() => {
        componentForDisplayBuilding.goToAddResposiblePerson(1);
        expect(router.navigate).toHaveBeenCalledWith(
          ['/addResposiblePerson',1]);
      });
  });

  it('should navigate to show all responsible people', () => {
    fixture.whenStable()
      .then(() => {
        componentForDisplayBuilding.goToShowAllResponsiblePersonsComponent(1);
        expect(router.navigate).toHaveBeenCalledWith(
          ['/showAllResponsiblePersonsComponent',1]);
      });
  });

  it('should navigate to set building manager', () => {
    fixture.whenStable()
      .then(() => {
        componentForDisplayBuilding.goToSetBuildingManagerComponent(1);
        expect(router.navigate).toHaveBeenCalledWith(
          ['/setBuildingManagerComponent',1]);
      });
  });

});
