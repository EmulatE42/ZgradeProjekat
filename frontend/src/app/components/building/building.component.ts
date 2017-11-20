import { Component } from '@angular/core';

import {Address, BuildingDTO} from "../../models";
import { AdminService } from "../../services/admin.service";

@Component({
  moduleId: module.id,
  selector: 'building',
  templateUrl: './building.component.html',
  styleUrls: ['./building.component.css'],
  providers: [AdminService]

})
export class BuildingComponent
{
  dateOfConstruction:Date;
  city:string;
  buildingNuber:string;
  buildingStreet:string;
  postalCode:string;
  country:string;

  constructor(private adminService: AdminService)
  {
  }

  addBuilding()
  {
    this.adminService.addBuilding(new BuildingDTO(null,this.dateOfConstruction,new Address(null,this.city,
      this.buildingNuber, this.buildingStreet, this.postalCode, this.country))).subscribe(
      data => console.log(data)
    );
  }
}


