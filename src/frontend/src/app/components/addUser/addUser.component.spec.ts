import { async, fakeAsync, ComponentFixture, TestBed,  tick } from '@angular/core/testing';

import { AddUserComponent } from './addUser.component';
import { AdminService } from "../../services/admin.service";
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { Address } from "../../models";
import { Router } from '@angular/router';

describe('AddUserComponent', () => {

  let component: AddUserComponent;
  let fixture: ComponentFixture<AddUserComponent>;
  let adminService1: any;
  let router: any;

  beforeEach(() => {
    let adminServiceMock1 = {
      registerUser: jasmine.createSpy('registerUser')
        .and.returnValue(Promise.resolve()),
    };

    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };


    TestBed.configureTestingModule({
      imports: [
        HttpModule,
        HttpClientModule,
        FormsModule,
        HttpClientTestingModule
        ],
      declarations: [ AddUserComponent ],
      providers:    [
        //{ provide: AdminService, useValue: adminServiceMock1 },
        { provide: Router, useValue: routerMock },
        AdminService
      ]
    });


    fixture = TestBed.createComponent(AddUserComponent);
    component    = fixture.componentInstance;
    adminService1 = TestBed.get(AdminService);
    router = TestBed.get(Router);
  });

  it('should register new user', () => {

    let usr = "";
    let password = "";
    let userType = "";
    let firstname = "";
    let lastname = "";
    let institutionName = "";
    let city = "";
    let buildingNumber = "";
    let buildingStreet = "";
    let postalCode = "";
    let country = "";
    let firmName = "";
    let firmDescription = "";


    //tick();
    fixture.whenStable()
      .then(() => {
        fixture.detectChanges();
        component.register();
        //expect(adminService1).toBeDefined();
        //expect(adminService1.registerUser);
        //tick();
        expect(adminService1.registerUser).toHaveBeenCalled();
        //component.register();

        //expect(adminService.registerUser).toHaveBeenCalledWith(usr, password, userType, firstname, lastname, institutionName,
         // new Address(null, city, buildingNumber, buildingStreet, postalCode, country), firmName, firmDescription);
    });

    //fixture.detectChanges();
    //component.register();
    //console.log("usaoooo2");
    //expect(adminService.registerUser).toHaveBeenCalled();

  });

});
