import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPageComponent } from './adminPage.component';
import {BuildingListItemDTO, BuildingDTO, LocationDTO, ResponsiblePersonDTO} from "../../models";
import { AdminService } from "../../services/admin.service";
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

describe('AdminPageComponent', () => {
  let component: AdminPageComponent;
  let fixture: ComponentFixture<AdminPageComponent>;
  let adminService: any;
  let router: any;

  beforeEach(() => {
    let adminServiceMock = {
      getAllBuildings: jasmine.createSpy('getAllBuildings')
        .and.returnValue(Promise.resolve(
          [
            new BuildingListItemDTO(1,'Novi Sad','Gogoeljeva','15'),
            new BuildingListItemDTO(2,'Bijeljina','Majora D. Gavrilovica', '14')
          ]
        )),

      deleteBuilding: jasmine.createSpy('deleteBuilding')
        .and.returnValue(Promise.resolve()),

      registerUser: jasmine.createSpy('registerUser')
        .and.returnValue(Promise.resolve()),

      addBuilding: jasmine.createSpy('addBuilding')
        .and.returnValue(Promise.resolve()),

      addLocation: jasmine.createSpy('addLocation')
        .and.returnValue(Promise.resolve()),

      getAllInstitutions: jasmine.createSpy('getAllInstitutions')
        .and.returnValue(Promise.resolve()),

      getAllTenants: jasmine.createSpy('getAllTenants')
        .and.returnValue(Promise.resolve()),

      addResponsiblePerson: jasmine.createSpy('addResponsiblePerson')
        .and.returnValue(Promise.resolve()),

      getBuildingById: jasmine.createSpy('getBuildingById')
        .and.returnValue(Promise.resolve(
          [
            new BuildingDTO(1,new Date(2017, 9, 22), null, [new LocationDTO(1,"APARTMENT",1,2,40,2,null)],null,null,null)
          ]
        )),

      deleteLocation: jasmine.createSpy('deleteLocation')
        .and.returnValue(Promise.resolve()),

      getAllTenantsFromBuilding: jasmine.createSpy('getAllTenantsFromBuilding')
        .and.returnValue(Promise.resolve()),

      setBuildingManager: jasmine.createSpy('setBuildingManager')
        .and.returnValue(Promise.resolve()),

      getAllResponsiblePeople: jasmine.createSpy('getAllResponsiblePeople')
        .and.returnValue(Promise.resolve(
          [
            new ResponsiblePersonDTO(1,null,null,true,"aaaaa")
          ]
        )),

      deleteResponsePerson: jasmine.createSpy('deleteResponsePerson')
        .and.returnValue(Promise.resolve()),
    };

    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    TestBed.configureTestingModule({
      imports: [
        HttpModule,
        HttpClientModule],
      declarations: [ AdminPageComponent ],
      providers:    [
        {provide: AdminService, useValue: adminServiceMock },
        { provide: Router, useValue: routerMock }
      ]
    });

    fixture = TestBed.createComponent(AdminPageComponent);
    component    = fixture.componentInstance;
    adminService = TestBed.get(AdminService);
    router = TestBed.get(Router);
  });

  it('should fetch the building list', async(() => {
    //TestBed.createComponent(AdminPageComponent);
    //expect(adminService.getAllBuildings).toHaveBeenCalled();
    fixture.whenStable()
      .then(() => {
        fixture.detectChanges();
        expect(adminService.getAllBuildings).toHaveBeenCalled();
      });
  }));

  it('should navigate to display building page', () => {
    component.goToDisplayBuilding(1);
    expect(router.navigate).toHaveBeenCalledWith(
      ['/displayBuilding',1]);
  });

  it('should navigate to make building page', () => {
    component.goToMakeBuilding();
    expect(router.navigate).toHaveBeenCalledWith(
      ['/makeBuilding']);
  });

  it ('should call deleteBuilding', () => {
    fixture.whenStable()
      .then(() => {
        fixture.detectChanges();
        component.deleteBuilding(1,1);
        expect(adminService.deleteBuilding).toHaveBeenCalledWith(1);
      });
  });

});


