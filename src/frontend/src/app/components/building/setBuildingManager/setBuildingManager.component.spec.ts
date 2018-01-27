import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { SetBuildingManagerComponent } from './setBuildingManager.component';
import { AdminService } from "../../../services/admin.service";
import { ActivatedRouteStub } from '../../../testing/router-stubs';
import { TenantDTO } from "../../../models";

describe('SetBuildingManagerComponent', () => {
  let componentForSetBuildingManager: SetBuildingManagerComponent;
  let fixture: ComponentFixture<SetBuildingManagerComponent>;
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
      declarations: [ SetBuildingManagerComponent ],
      providers:    [
        AdminService,
        { provide: Router, useValue: routerMock },
        { provide: ActivatedRoute, useValue: activatedRouteStub }
      ]
    });

    fixture = TestBed.createComponent(SetBuildingManagerComponent);
    componentForSetBuildingManager = fixture.componentInstance;
    adminService = TestBed.get(AdminService);
    router = TestBed.get(Router);
  });

  it('should get building by id', () => {
    fixture.whenStable()
      .then(() => {
        expect(adminService.getBuildingById).toHaveBeenCalled();
      });
  });

  it('should get all tenants from building', () => {
    componentForSetBuildingManager.getAllTenantsFromBuilding();
    fixture.whenStable()
      .then(() => {
        expect(adminService.getAllTenantsFromBuilding).toHaveBeenCalled();
      });
  });

  it('should save new building manager', () => {
    fixture.whenStable()
      .then(() => {
      componentForSetBuildingManager.newBuildingManager = new TenantDTO(1,"pero","pero","pero",false);
      componentForSetBuildingManager.save();
      fixture.whenStable()
        .then(() => {
          expect(adminService.setBuildingManager).toHaveBeenCalled();
        });
      });
  });
});
