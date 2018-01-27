import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';

import {LoggedUtils} from "../utils/logged.utils";
import {BugDTO, CommentDTO, UserDTO} from "../models";

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};

@Injectable()
export class TenantService {
  constructor(private http: HttpClient) {
  }

  getApartmentsOfTenant() {
    let tenantId = LoggedUtils.getId();
    return this.http.get("http://localhost:8080/user/getApartmentsOfTenant/" + tenantId);
  }

  getAllBugs(locationId: number) {
    return this.http.get("http://localhost:8080/bug/getAllBugs/" + locationId);
  }

  addNewBug(locationId: number, bugDTO: BugDTO) {
    var param = JSON.stringify(bugDTO);
    return this.http.post("http://localhost:8080/bug/reportBug/" + locationId, param, httpOptions);
  }

  deleteBug(bugId: number, locationId: number) {
    return this.http.delete("http://localhost:8080/bug/deleteBug/" + bugId + "/" + locationId);
  }

  addComment(bugId: number, commentDTO: CommentDTO) {
    commentDTO.user = new UserDTO(LoggedUtils.getId(), LoggedUtils.getUsername());
    var param = JSON.stringify(commentDTO);
    return this.http.post("http://localhost:8080/bug/addComment/" + bugId, param, httpOptions);
  }

  getBug(bugId: number) {
    return this.http.get("http://localhost:8080/bug/getBug/" + bugId);
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
    return this.http.delete("http://localhost:8080/bug/deleteComment/" +commentId +"/" + bugId);
  }

  getBill(bugId:number)
  {
    return this.http.get("http://localhost:8080/bill/getBill/" + bugId);
  }

  payBill(bugId:number)
  {
    return this.http.put("http://localhost:8080/bill/payBill/" + bugId, httpOptions);
  }


}
