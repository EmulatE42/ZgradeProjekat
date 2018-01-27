import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';

import {LoggedUtils} from "../utils/logged.utils";
import {Bill} from "../models";

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};

@Injectable()
export class FirmService {

  constructor(private http: HttpClient) {
  }

  getBugsOfFirm()
  {
    let firmId = LoggedUtils.getId();
    return this.http.get("http://localhost:8080/bug/getBugsOfFirm/"+firmId);
  }

  makeBill(bugId:number,bill:Bill)
  {
    var param = JSON.stringify(bill);
    return this.http.post("http://localhost:8080/bill/makeBill/"+bugId, param, httpOptions);
  }

}
