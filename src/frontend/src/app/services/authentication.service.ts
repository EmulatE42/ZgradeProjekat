import { Injectable, EventEmitter, Output } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import {Subject} from 'rxjs/Subject';
import {Observable} from 'rxjs/Observable';

import {LoggedUtils} from "../utils/logged.utils";

@Injectable()
export class AuthenticationService
{
  //@Output() getLoggedInName: EventEmitter<string> = new EventEmitter();
  //@Output() getLoggedInName = new EventEmitter<string>();
  public OnShowToast = new Subject<string>();

  constructor(private http: Http) {}

  authenticateUser(username: string, pass: string)
  {
    let authenticationRequest = {username: username, password: pass};
    let param = JSON.stringify(authenticationRequest);
    let headers = new Headers();

    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/api/login", param, { headers : headers })
      .map(res => res.json());
  }

  emitRole(urs:string)
  {
    if(urs!="")
    {
      console.log("usaoooo");
      console.log(LoggedUtils.getRole());
      this.OnShowToast.next(LoggedUtils.getRole());
      //this.getLoggedInName.emit("asdasd");
      //this.getLoggedInName.emit(LoggedUtils.getRole());
      //return true;
    }
  }

  getMessage(): Observable<any> {
    return this.OnShowToast.asObservable();
  }

}
