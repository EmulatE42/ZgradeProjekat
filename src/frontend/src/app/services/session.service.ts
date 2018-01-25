import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';

import {LoggedUtils} from "../utils/logged.utils";
import {SessionDTO} from "../models";

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};

@Injectable()
export class SessionService {

  constructor(private http: HttpClient) {}

  getSessions(id: number) {
    return this.http.get("http://localhost:8080/parlament/getSessions/" + id);
  }

  getSession(id: number) {
    return this.http.get("http://localhost:8080/session/getById/" + id);
  }

  addSession(session: SessionDTO) {
    var param = JSON.stringify(session);
    return this.http.post("http://localhost:8080/session/add", param, httpOptions);
  }

  isBuildingManager() {
    let tenantId = LoggedUtils.getId();
    return this.http.get("http://localhost:8080/session/isBuildingManager/" + tenantId, {observe: 'response'});

  }


  getId() {
    return LoggedUtils.getId();
  }

  getUsername()
  {
    return LoggedUtils.getUsername();
  }

}
