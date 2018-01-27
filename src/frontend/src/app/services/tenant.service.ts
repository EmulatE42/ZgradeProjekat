import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

import {LoggedUtils} from "../utils/logged.utils";
import {BugDTO, CommentDTO, UserDTO} from "../models";

@Injectable()
export class TenantService {
  constructor(private http: Http) {
  }

  getApartmentsOfTenant() {
    let tenantId = LoggedUtils.getId();
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/user/getApartmentsOfTenant/" + tenantId, {headers: headers})
      .map(res => res.json());
  }

  getAllBugs(locationId: number) {
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/bug/getAllBugs/" + locationId, {headers: headers})
      .map(res => res.json());
  }

  addNewBug(locationId: number, bugDTO: BugDTO) {
    var param = JSON.stringify(bugDTO);
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/bug/reportBug/" + locationId, param, {headers: headers})
      .map(res => res.json());
  }

  deleteBug(bugId: number, locationId: number) {
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.delete("http://localhost:8080/bug/deleteBug/" + bugId + "/" + locationId, {headers: headers})
      .map(res => res.json());
  }

  addComment(bugId: number, commentDTO: CommentDTO) {
    commentDTO.user = new UserDTO(LoggedUtils.getId(), LoggedUtils.getUsername());
    var param = JSON.stringify(commentDTO);
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/bug/addComment/" + bugId, param, {headers: headers})
      .map(res => res.json());
  }

  getBug(bugId: number) {
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.get("http://localhost:8080/bug/getBug/" + bugId, {headers: headers})
      .map(res => res.json());
  }

  getId() {
    return LoggedUtils.getId();
  }

  getUsername()
  {
    return LoggedUtils.getUsername();
  }

  deleteComment(commentId:number, bugId:number)
  {
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.delete("http://localhost:8080/bug/deleteComment/" +commentId +"/" + bugId, {headers: headers})
      .map(res => res.json());
  }

  getBill(bugId:number)
  {
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.get("http://localhost:8080/bill/getBill/" + bugId, {headers: headers})
      .map(res => res.json());
  }

  payBill(bugId:number)
  {
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put("http://localhost:8080/bill/payBill/" + bugId, {headers: headers})
      .map(res => res.json());
  }


}
