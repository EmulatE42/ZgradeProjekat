import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';

import {LoggedUtils} from "../utils/logged.utils";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Parliament} from "../models";

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};

@Injectable()
export class ParlamentService {

  constructor(private http: HttpClient) {}

  getParlaments() {
    let tenantId = LoggedUtils.getId();
    return this.http.get("http://localhost:8080/user/getParliamentsOfTenant/" + tenantId);
  }

  addParlament(parlament: Parliament) {
    var param = JSON.stringify(parlament);
    return this.http.post("http://localhost:8080/parlament/add", param, httpOptions);
  }


  getId() {
    return LoggedUtils.getId();
  }

  getUsername()
  {
    return LoggedUtils.getUsername();
  }

}
