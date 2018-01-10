import { Injectable } from '@angular/core';
import { Http, Headers,RequestOptions} from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';

import {LoggedUtils} from "../utils/logged.utils";
import {BuildingDTO, LocationDTO, ResponsiblePersonDTO, Address, BuildingListItemDTO} from "../models";

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};

@Injectable()
export class AdminService
{
  constructor(private http: HttpClient)
  {
  }

  registerUser(username: string, pass: string, role: string, firstname: string, lastname: string, institutionName: string, address: Address, firmName: string, firmDescription: string)
  {
    let registerRequest = {username: username, password: pass, role: role, firstname: firstname, lastname: lastname, institutionName: institutionName, address: address, firmName: firmName, firmDescription: firmDescription};
    let param = JSON.stringify(registerRequest);
    return this.http.post("http://localhost:8080/api/registerUser", param, httpOptions);

  }

  addBuilding(building : BuildingDTO)
  {
    var param = JSON.stringify(building);
    return this.http.post("http://localhost:8080/building/add", param, httpOptions);
  }

  addAllBuildings()
  {
    return this.http.get<BuildingListItemDTO[]>("http://localhost:8080/building/getAllBuildings", httpOptions);
  }

  deleteBuilding(buildingId:number)
  {
    return this.http.delete("http://localhost:8080/building/deleteBuilding/"+buildingId);
  }

  getBuildingById(buildingId:number)
  {
    return this.http.get("http://localhost:8080/building/findByBuildingId/"+buildingId);
  }

  addLocation(location : LocationDTO)
  {
    var param = JSON.stringify(location);
    return this.http.post("http://localhost:8080/building/addLocationToBuilding", param, httpOptions);
  }

  deleteLocation(locationId : number)
  {
    return this.http.delete("http://localhost:8080/location/deleteLocation/"+locationId);
  }

  getLocationById(locationId : number)
  {
    return this.http.get("http://localhost:8080/location/findByLocationId/"+locationId);
  }

  getAllTenants()
  {
    return this.http.get("http://localhost:8080/user/getAllTenants");
  }

  connectTenantAndApartment(apartmentId:number, tenantId:number)
  {
    return this.http.put("http://localhost:8080/location/connectTenantAndApartment/"+apartmentId+"/"+tenantId, httpOptions);
  }

  getAllInstitutions()
  {
    return this.http.get("http://localhost:8080/user/getAllInstitution");
  }

  addResponsiblePerson(responsiblePerson:ResponsiblePersonDTO, buildingId:number)
  {
    var param = JSON.stringify(responsiblePerson);
    return this.http.post("http://localhost:8080/building/addResponsiblePerson/"+buildingId, param, httpOptions);
  }

  getAllResponsiblePeople(id:number)
  {
    return this.http.get("http://localhost:8080/building/getAllResponsiblePersons/"+id);
  }

  deleteResponsePerson(id:number, buildingId:number)
  {
    return this.http.delete("http://localhost:8080/building/deleteResponsiblePerson/"+id+"/"+buildingId);
  }

  getAllTenantsFromBuilding(buildingId:number)
  {
    return this.http.get("http://localhost:8080/user/getAllTenantsFromBuilding/"+buildingId);
  }

  setBuildingManager(buildingId:number, tenantId:number)
  {
    return this.http.put("http://localhost:8080/building/setBuildingManager/"+buildingId+"/"+tenantId, httpOptions);
  }

  getAllResponsiblePersonsByLocationId(id:number)
  {
    return this.http.get("http://localhost:8080/building/getAllResponsiblePersonsByLocationId/"+id);
  }


}


