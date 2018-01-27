import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

import {LoggedUtils} from "../utils/logged.utils";
import {BugDTO, CommentDTO, UserDTO} from "../models";

@Injectable()
export class InstitutionService {

  constructor(private http: Http) {
  }

  getBugsOfResponsiblePerson() {
    let userId = LoggedUtils.getId();
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/bug/getBugsOfResponsiblePerson/" + userId, {headers: headers})
      .map(res => res.json());
  }

  getAllFirms()
  {
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/user/getAllFirms", {headers: headers})
      .map(res => res.json());
  }

  connectBugAndFirm(bugId:number, firmId:number)
  {
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.put("http://localhost:8080/bug/connectBugAndFirm/"+bugId+"/"+firmId, {headers: headers})
      .map(res => res.json());
  }

}
