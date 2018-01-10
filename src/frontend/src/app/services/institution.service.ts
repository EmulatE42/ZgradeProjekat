import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';

import {LoggedUtils} from "../utils/logged.utils";
import {BugDTO, CommentDTO, UserDTO} from "../models";

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};

@Injectable()
export class InstitutionService {

  constructor(private http: HttpClient) {
  }

  getBugsOfResponsiblePerson() {
    let userId = LoggedUtils.getId();
    return this.http.get("http://localhost:8080/bug/getBugsOfResponsiblePerson/" + userId);
  }

  getAllFirms()
  {
    return this.http.get("http://localhost:8080/user/getAllFirms");
  }

  connectBugAndFirm(bugId:number, firmId:number)
  {
    return this.http.put("http://localhost:8080/bug/connectBugAndFirm/"+bugId+"/"+firmId, httpOptions);
  }

}
