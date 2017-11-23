import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

import {LoggedUtils} from "../utils/logged.utils";
import {BuildingDTO, LocationDTO} from "../models";

@Injectable()
export class AdminService
{
  constructor(private http: Http) {}

  registerUser(username: string, pass: string, role: string, firstname: string, lastname: string)
  {
    let registerRequest = {username: username, password: pass, role: role, firstname: firstname, lastname: lastname};
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
    return this.http.get("http://localhost:8080/building/getAllBuildings",{ headers : headers })
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

  getBuildingById(buildingId:number)
  {
    var headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.get("http://localhost:8080/building/findByBuildingId/"+buildingId,{ headers : headers })
      .map(res => res.json());
  }


  addLocation(location : LocationDTO)
  {
    var param = JSON.stringify(location);
    var headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    console.log(location);
    return this.http.post("http://localhost:8080/building/addLocationToBuilding", param, { headers : headers })
      .map(res => res.json());
  }

  deleteLocation(locationId : number)
  {
    var headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.delete("http://localhost:8080/location/deleteLocation/"+locationId, { headers : headers })
      .map(res => res.json());
  }

  getLocationById(locationId : number)
  {
    var headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.get("http://localhost:8080/location/findByLocationId/"+locationId, { headers : headers })
      .map(res => res.json());
  }

  getAllTenants()
  {
    var headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.get("http://localhost:8080/user/getAllTenants", { headers : headers })
      .map(res => res.json());
  }

  connectTenantAndApartment(apartmentId:number, tenantId:number)
  {
    var headers = new Headers();
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.put("http://localhost:8080/location/connectTenantAndApartment/"+apartmentId+"/"+tenantId, { headers : headers })
      .map(res => res.json());
  }
}


