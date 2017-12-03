import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

import {LoggedUtils} from "../utils/logged.utils";

@Injectable()
export class ParlamentService {

  constructor(private http: Http) {}

  getParlaments() {
    let tenantId = LoggedUtils.getId();
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/user/getParliamentsOfTenant/" + tenantId, {headers: headers})
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
