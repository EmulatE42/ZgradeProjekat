import { Component } from '@angular/core';

import {Address, BuildingDTO, ParliamentDTO, Parliament} from "../../models";
import { AdminService } from "../../services/admin.service";
import { Router }    from '@angular/router';
import {ParlamentService} from "../../services/parlament.service";

@Component({
  moduleId: module.id,
  selector: 'building',
  templateUrl: './building.component.html',
  styleUrls: ['./building.component.css'],
  providers: [AdminService, ParlamentService]

})
export class BuildingComponent
{
  dateOfConstruction:Date;
  city:string;
  buildingNumber:string;
  buildingStreet:string;
  postalCode:string;
  country:string;
  parlament: Parliament;

  constructor(private adminService: AdminService, private parlamentService: ParlamentService, private _router: Router)
  {
  }

  addBuilding()
  {
    this.parlamentService.addParlament(new Parliament(null, null)).subscribe(
      (data: Parliament) => this.parlament = data,
      err => alert(err),
      () => {
        this.adminService.addBuilding(new BuildingDTO(null,this.dateOfConstruction,new Address(null,this.city,
          this.buildingNumber, this.buildingStreet, this.postalCode, this.country),null,null,null, new ParliamentDTO(this.parlament.id, null, null))).subscribe(
          ()=>this.goToDisplayAllBuildings()
        );
      }
    );
  }

  goToDisplayAllBuildings()
  {
    this._router.navigate(['/adminPage']);
  }
}


