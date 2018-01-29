import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {PublicNotificationDTO} from "../models";

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};


@Injectable()
export class PublicNotificationService {

  constructor(private http: HttpClient) {
  }

  addPublicNotification(pn: PublicNotificationDTO) {
    var param = JSON.stringify(pn);
    return this.http.post("http://localhost:8080/publicNotification/addPn", param, httpOptions);
  }

  getAllPublicNotifications() {

    return this.http.get("http://localhost:8080/publicNotification/getAllPns");
  }

}
