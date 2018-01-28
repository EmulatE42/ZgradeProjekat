import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';


import { ParlamentViewComponent } from './parlamentView.component';
import { ParlamentService } from "../../services/parlament.service";
import {ParliamentDTO, SessionDTO, TopicDTO, UserDTO} from "../../models";


describe('ParlamentViewComponent', () => {
  let componentForParlamentViewComponent: ParlamentViewComponent;
  let fixture: ComponentFixture<ParlamentViewComponent>;
  let parlamentService: any;
  let router: any;

  beforeEach(() => {

    let parlamentServiceMock = {

      getParlaments: jasmine.createSpy('getParlaments')
        .and.returnValue(Promise.resolve(
          [
            new ParliamentDTO(1, null, null)
          ]
        )),

    };

    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };


    TestBed.configureTestingModule({
      imports: [
        HttpModule,
        HttpClientModule,
      ],
      declarations: [ ParlamentViewComponent ],
      providers:    [
        {provide: ParlamentService, useValue: parlamentServiceMock },
        { provide: Router, useValue: routerMock },
      ]
    }).compileComponents().then(() => {

      fixture = TestBed.createComponent(ParlamentViewComponent);
      componentForParlamentViewComponent  = fixture.componentInstance;
      parlamentService = TestBed.get(ParlamentService);
      router = TestBed.get(Router);
    });

  });

  it('should create', () => {
    expect(componentForParlamentViewComponent).toBeTruthy();
  });

  // it('should get parlament list', async(() => {
  //   fixture.whenStable()
  //     .then(() => {
  //       expect(parlamentService.getParlaments).toHaveBeenCalled();
  //     });
  // }));
  //
  // it('should navigate to session page', () => {
  //   componentForParlamentViewComponent.goToLink(1);
  //   expect(router.navigate).toHaveBeenCalledWith(
  //     ['/parlament',1,'sessions']);
  // });
});
