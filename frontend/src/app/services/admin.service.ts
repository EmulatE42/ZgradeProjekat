import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

import {LoggedUtils} from "../utils/logged.utils";
import {BuildingDTO} from "../models";

@Injectable()
export class AdminService
{
  constructor(private http: Http) {}

  registerUser(username: string, pass: string, role: string)
  {
    let registerRequest = {username: username, password: pass, role: role};
    let param = JSON.stringify(registerRequest);
    let headers = new Headers();

    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/api/registerUser", param, { headers : headers })
      .map(res => res.json());
  }

  addBuilding(building : BuildingDTO)
  {
    var param = JSON.stringify(building);
    console.log(param);
    var headers = new Headers();
    console.log("Token:   "+LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/building/add", param, { headers : headers })
      .map(res => res.json());
  }

  addAllBuildings()
  {
    var headers = new Headers();
    console.log("Token:   "+LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.get("http://localhost:8080/building/getAllBuilding",{ headers : headers })
      .map(res => res.json());
  }

  deleteBuilding(buildingId:number)
  {
    var headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.delete("http://localhost:8080/building/deleteBuilding/"+buildingId,{ headers : headers })
      .map(res => res.json());
  }


}


