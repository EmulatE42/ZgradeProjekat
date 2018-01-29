import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { PublicNotificationComponent } from './public-notification.component';
import {ActivatedRoute, Router} from "@angular/router";
import {PublicNotificationService} from "../../../services/public-notification.service";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";

describe('PublicNotificationComponent', () => {
  let componentForAddNewpn: PublicNotificationComponent;
  let fixture: ComponentFixture<PublicNotificationComponent>;
  let publicNotificationService: any;
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
      declarations: [ PublicNotificationComponent ],
      providers:    [
        PublicNotificationService,
        { provide: Router, useValue: routerMock },
        { provide: ActivatedRoute }
      ]
    });

    fixture = TestBed.createComponent(PublicNotificationComponent);
    componentForAddNewpn = fixture.componentInstance;
    publicNotificationService = TestBed.get(PublicNotificationService);
    router = TestBed.get(Router);
  });
    it('should add new public notification', () => {
      fixture.whenStable()
        .then(() => {
          expect(publicNotificationService.addPublicNotification).toHaveBeenCalled();
        });
    });

  });
