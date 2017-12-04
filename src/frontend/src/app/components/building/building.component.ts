import { Component } from '@angular/core';

import {Address, BuildingDTO, ParliamentDTO} from "../../models";
import { AdminService } from "../../services/admin.service";
import { Router }    from '@angular/router';

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
  buildingNumber:string;
  buildingStreet:string;
  postalCode:string;
  country:string;

  constructor(private adminService: AdminService, private _router: Router)
  {
  }

  addBuilding()
  {
    this.adminService.addBuilding(new BuildingDTO(null,this.dateOfConstruction,new Address(null,this.city,
      this.buildingNumber, this.buildingStreet, this.postalCode, this.country),null,null,null, new ParliamentDTO(null, null, null))).subscribe(
      ()=>this.goToDisplayAllBuildings()
    );
  }

  goToDisplayAllBuildings()
  {
    this._router.navigate(['/adminPage']);
  }
}


