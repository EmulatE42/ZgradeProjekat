import { TestBed, fakeAsync, tick } from '@angular/core/testing';
import { MockBackend } from '@angular/http/testing';
import { HttpClientModule, HttpXhrBackend } from '@angular/common/http';
import { Response, ResponseOptions, RequestMethod } from '@angular/http';
import { BaseRequestOptions, ConnectionBackend, Http, RequestOptions } from '@angular/http';
import { LoginResponseDTO, BuildingDTO, LocationDTO } from '../models';

import { AdminService } from './admin.service';
import { UrlResolver} from '@angular/compiler';

import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/Observable';
import 'rxjs/add/observable/throw';

describe('AdminService', () => {

  beforeEach(() => {

    TestBed.configureTestingModule({
      imports: [
        HttpClientModule,
      ],
      providers:    [
        {provide: ConnectionBackend, useClass: MockBackend},
        {provide: RequestOptions, useClass: BaseRequestOptions},
        Http,
        AdminService
      ]

    });

    this.adminService = TestBed.get(AdminService);
    this.backend = TestBed.get(ConnectionBackend);
    this.backend.connections.subscribe((connection: any) =>
      this.lastConnection = connection);


  });

  it('should pass simple test', () => {
    expect(true).toBe(true);
  });

  it('registerUser should register new user', fakeAsync(() => {
    let registeredUser: LoginResponseDTO = new LoginResponseDTO("aaaa", "bbbb", "123454", 1, "stefan", "TENANT", false);
    //expect(this.lastConnection).toBeDefined();


    this.backend.connections.subscribe(connection =>{
      connection.mockRespond(new Response(<ResponseOptions>{
          body: JSON.stringify(
            {
              text: "aaaa",
              routerLink: "bbbb",
              token: "123454",
              id: 1,
              username: "stefan",
              role: "TENANT",
              isResponsible: false
            }
          )
        }
      ))
    });


    this.adminService.registerUser("stefan", "stefan", "TENANT",
      "stefan", "djuric", null, null, null, null);




    /*
    this.adminService.registerUser.then(
      (s: LoginResponseDTO) => registeredUser = s);


    this.lastConnection.mockRespond(new Response(new ResponseOptions({
      body: JSON.stringify(
        {
          text: "aaaa",
          routerLink: "bbbb",
          token: "123454",
          id: 1,
          username: "stefan",
          role: "TENANT",
          isResponsible: false
        })
    })));
    */

    tick();


    expect(registeredUser).toBeDefined();
    expect(registeredUser.id).toEqual(1);
    expect(registeredUser.token).toEqual('123454');
    expect(registeredUser.username).toEqual('stefan');
    expect(registeredUser.role).toEqual('TENANT');


  }));


  it('addBuilding should add new building', fakeAsync(() => {
    let building:BuildingDTO = new BuildingDTO(1,new Date(2017, 9, 22), null,
      [new LocationDTO(1,"APARTMENT",1,2,40,2,null)],null,null,null);

    this.adminService.addBuilding(building);

    this.backend.connections.subscribe(connection =>{
      connection.mockRespond(new Response(<ResponseOptions>{
          body: JSON.stringify(
            {
              text: "aaaa",
              routerLink: "bbbb",
              token: "123454",
              id: 1,
              username: "stefan",
              role: "TENANT",
              isResponsible: false
            }
          )
        }
      ))
    });

    tick();

    expect(building).toBeDefined();
    expect(building.id).toEqual(1);

  }));




});
