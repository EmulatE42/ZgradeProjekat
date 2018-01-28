import { async, fakeAsync, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import {SessionViewComponent} from "./sessionView.component";
import {SessionDTO, TopicDTO} from "../../models";
import {ActivatedRouteStub} from "../../testing/router-stubs";
import {TopicService} from "../../services/topic.service";
import {SessionService} from "../../services/session.service";


describe('SessionViewComponent', () => {
  let component: SessionViewComponent;
  let fixture: ComponentFixture<SessionViewComponent>;
  let activatedRoute: any;
  let sessionService: any;
  let topicService: any;


  beforeEach(() => {
    let sessionServiceMock = {
      isBuildingManager: jasmine.createSpy('isBuildingManager')
        .and.returnValue(Promise.resolve()),

      getSessions: jasmine.createSpy('getSessions')
        .and.returnValue(Promise.resolve(
          [
            new SessionDTO(1,null,null,null,null,null,null),
            new SessionDTO(2,null,null,null,null,null,null)

          ])),

      getSession: jasmine.createSpy('getSession')
        .and.returnValue(Promise.resolve())
    };

    let topicServiceMock = {
      getTopics: jasmine.createSpy('getTopics')
        .and.returnValue(Promise.resolve(
          [
            new TopicDTO(1, null, null, false, 0, 0),
            new TopicDTO(2, null, null, false, 0, 0)
          ]
        ))
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
      declarations: [ SessionViewComponent ],
      providers:    [
        { provide: TopicService, useValue: topicServiceMock },
        { provide: SessionService, useValue: sessionServiceMock },
        { provide: ActivatedRoute, useValue: activatedRouteStub },
        { provide: Router, useValue: routerMock }
      ]
    }).compileComponents().then(() => {

      fixture = TestBed.createComponent(SessionViewComponent);
      component = fixture.componentInstance;
      sessionService = TestBed.get(SessionService);
      topicService = TestBed.get(TopicService);
      activatedRoute = TestBed.get(ActivatedRoute);
    });

  });

  it('should get is tenant building manager', async(() => {
    fixture.whenStable()
      .then(() => {
        fixture.detectChanges();
        expect(sessionService.isBuildingManager).toHaveBeenCalled();
      });
  }));

  it('should get all sessions', async(() => {
    fixture.whenStable()
      .then(() => {
        fixture.detectChanges();
        expect(sessionService.getSessions).toHaveBeenCalled();
      });
  }));

  it('should get session', () => {
    fixture.whenStable()
      .then(() => {
        fixture.detectChanges();
        expect(sessionService.getSession).toHaveBeenCalled();
      });
  });

  it('should get topics', () => {
    fixture.whenStable()
      .then(() => {
        fixture.detectChanges();
        expect(topicService.getTopics).toHaveBeenCalled();
      });
  });
});
