import { async, fakeAsync, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';


import { SetResponsibleFirmComponent } from './setResponsibleFirm.component';
import {BugDTO,FirmDTO} from "../../../models";
import { TenantService } from "../../../services/tenant.service";
import { InstitutionService } from "../../../services/institution.service";
import { ActivatedRouteStub } from '../../../testing/router-stubs';


describe('SetResponsibleFirmComponent', () => {
  let componentForSetResponsibleFirm: SetResponsibleFirmComponent;
  let fixture: ComponentFixture<SetResponsibleFirmComponent>;
  let tenantService: any;
  let institutionService: any;
  let activatedRoute: any;
  let location: any;

  beforeEach(() => {
    let tenantServiceMock = {
      getBug: jasmine.createSpy('getBug')
        .and.returnValue(Promise.resolve(
          new BugDTO(1, "problem sa strujom", new Date(2017, 9, 22), null, false, null,
            new FirmDTO(1, "aaaa", null, "bbb", "cccc"),false)
        )),

      getApartmentsOfTenant: jasmine.createSpy('getApartmentsOfTenant')
        .and.returnValue(Promise.resolve()),

      addNewBug: jasmine.createSpy('addNewBug')
        .and.returnValue(Promise.resolve())
    };

    let institutionServiceMock = {
      getAllFirms: jasmine.createSpy('getAllFirms')
        .and.returnValue(Promise.resolve(
          [
            new FirmDTO(1, "aaaa", null, "bbb", "cccc"),
            new FirmDTO(2, "bbb", null, "nnnn", "kkkk")
          ]
        )),
      connectBugAndFirm: jasmine.createSpy('connectBugAndFirm')
        .and.returnValue(Promise.resolve())
    };

    let activatedRouteStub: ActivatedRouteStub = new ActivatedRouteStub();
    activatedRouteStub.testParams = { id: 1 }

    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    TestBed.configureTestingModule({
      imports: [
        HttpModule,
        HttpClientModule,
        FormsModule
      ],
      declarations: [ SetResponsibleFirmComponent ],
      providers:    [
        { provide: TenantService, useValue: tenantServiceMock },
        { provide: InstitutionService, useValue: institutionServiceMock },
        { provide: ActivatedRoute, useValue: activatedRouteStub },
        { provide: Router, useValue: routerMock }
      ]
    });

    fixture = TestBed.createComponent(SetResponsibleFirmComponent);
    componentForSetResponsibleFirm = fixture.componentInstance;
    tenantService = TestBed.get(TenantService);
    institutionService = TestBed.get(InstitutionService);
    activatedRoute = TestBed.get(ActivatedRoute);
  });

  it('should get bug', async(() => {
    componentForSetResponsibleFirm.ngOnInit();
      fixture.whenStable()
        .then(() => {
          fixture.detectChanges();
            expect(tenantService.getBug).toHaveBeenCalled();
      });
  }));

  it('should get all firms', async(() => {
    fixture.whenStable()
      .then(() => {
        fixture.detectChanges();
        expect(institutionService.getAllFirms).toHaveBeenCalled();
      });
  }));

  it('should set responsible firm', () => {
    componentForSetResponsibleFirm.responsibleFirm = new FirmDTO(1, "aaaa", null, "bbb", "cccc");
    fixture.whenStable()
      .then(() => {
        fixture.detectChanges();
        componentForSetResponsibleFirm.save();
        expect(institutionService.connectBugAndFirm).toHaveBeenCalled();
      });
  });
});


