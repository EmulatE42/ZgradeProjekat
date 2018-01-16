import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';


import { BuildingComponent } from './building.component';
import { AdminService } from "../../services/admin.service";

describe('BuildingComponent', () => {
  let componentForBuilding: BuildingComponent;
  let fixture: ComponentFixture<BuildingComponent>;
  let adminService: any;
  let router: any;

  beforeEach(() => {
    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    TestBed.configureTestingModule({
      imports: [
        HttpModule,
        HttpClientModule,
        FormsModule
      ],
      declarations: [ BuildingComponent ],
      providers:    [
        AdminService,
        { provide: Router, useValue: routerMock }
      ]
    });

    fixture = TestBed.createComponent(BuildingComponent);
    componentForBuilding = fixture.componentInstance;
    adminService = TestBed.get(AdminService);
    router = TestBed.get(Router);
  });

  it('should add building', () => {
    fixture.whenStable()
      .then(() => {
        fixture.detectChanges();
        componentForBuilding.addBuilding();
        expect(adminService.addBuilding).toHaveBeenCalled();
      });
  });

  it('should navigate to display admin page', () => {
    componentForBuilding.goToDisplayAllBuildings();
    expect(router.navigate).toHaveBeenCalledWith(
      ['/adminPage']);
  });
});
