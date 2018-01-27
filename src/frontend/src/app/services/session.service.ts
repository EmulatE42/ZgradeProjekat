import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

import {LoggedUtils} from "../utils/logged.utils";
import {SessionDTO} from "../models";

@Injectable()
export class SessionService {

  constructor(private http: Http) {}

  getSessions(id: number) {
    let tenantId = LoggedUtils.getId();
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/parlament/getSessions/" + id, {headers: headers})
      .map(res => res.json());
  }

  addSession(session: SessionDTO) {
    var param = JSON.stringify(session);
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/session/add", param, {headers: headers})
      .map(res => res.json());
  }


  getId() {
    return LoggedUtils.getId();
  }

  getUsername()
  {
    return LoggedUtils.getUsername();
  }

}
