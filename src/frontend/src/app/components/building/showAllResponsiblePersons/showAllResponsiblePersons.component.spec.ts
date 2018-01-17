import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { ShowAllResponsiblePersonsComponent } from './showAllResponsiblePersons.component';
import { AdminService } from "../../../services/admin.service";
import { ActivatedRouteStub } from '../../../testing/router-stubs';

describe('ShowAllResponsiblePersonsComponent', () => {
  let componentForShowAllResponsiblePerson: ShowAllResponsiblePersonsComponent;
  let fixture: ComponentFixture<ShowAllResponsiblePersonsComponent>;
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
      declarations: [ ShowAllResponsiblePersonsComponent ],
      providers:    [
        AdminService,
        { provide: Router, useValue: routerMock },
        { provide: ActivatedRoute, useValue: activatedRouteStub }
      ]
    });

    fixture = TestBed.createComponent(ShowAllResponsiblePersonsComponent);
    componentForShowAllResponsiblePerson = fixture.componentInstance;
    adminService = TestBed.get(AdminService);
    router = TestBed.get(Router);
  });

  it('should get all responsible people', () => {
    fixture.whenStable()
      .then(() => {
        fixture.detectChanges();
        expect(adminService.getAllResponsiblePeople).toHaveBeenCalled();
      });
  });

  it('should delete responsible person', () => {
    fixture.whenStable()
      .then(() => {
      componentForShowAllResponsiblePerson.delete(1,1);
      fixture.whenStable()
        .then(() => {
          fixture.detectChanges();
          expect(adminService.deleteResponsePerson).toHaveBeenCalled();
        });
      });
  });

});
