import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

import {LoggedUtils} from "../utils/logged.utils";
import {Bill} from "../models";

@Injectable()
export class FirmService {

  constructor(private http: Http) {
  }

  getBugsOfFirm()
  {
    let firmId = LoggedUtils.getId();
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/bug/getBugsOfFirm/"+firmId, {headers: headers})
      .map(res => res.json());
  }

  makeBill(bugId:number,bill:Bill)
  {
    var param = JSON.stringify(bill);
    var headers = new Headers();
    console.log("Token:   "+LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/bill/makeBill/"+bugId, param, { headers : headers })
      .map(res => res.json());
  }

}
