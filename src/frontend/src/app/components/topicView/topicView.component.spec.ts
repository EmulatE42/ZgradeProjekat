import { async, fakeAsync, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import {TopicViewComponent} from "./topicView.component";


describe('TopicViewComponent', () => {
  let component: TopicViewComponent;
  let fixture: ComponentFixture<TopicViewComponent>;
  let activatedRoute: any;
  let sessionService: any;
  let topicService: any;


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
      declarations: [ TopicViewComponent ],
      providers:    [
        { provide: Router, useValue: routerMock }
      ]
    }).compileComponents().then(() => {

      fixture = TestBed.createComponent(TopicViewComponent);
      component = fixture.componentInstance;
    });

  });


  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
