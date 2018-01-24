import { Component } from '@angular/core';

import {BuildingListItemDTO} from "../../models";
import { AdminService } from "../../services/admin.service";
import { Router } from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'adminPage',
  templateUrl: './adminPage.component.html',
  styleUrls: ['./adminPage.component.css'],
  providers: [AdminService]

})
export class AdminPageComponent
{
  buildings:BuildingListItemDTO[];

  constructor(private adminService: AdminService, private _router: Router)
  {
     this.adminService.getAllBuildings().subscribe(
       data => this.buildings = data,
       error => alert(error)
     );
  }

  deleteBuilding(buildingId:number,index:number)
  {
    this.buildings.splice(index,1);
    this.adminService.deleteBuilding(buildingId).subscribe(
      data=>console.log(data)
    );
  }

  goToBuilding(buildingId:number)
  {
    this.adminService.getBuildingById(buildingId).subscribe(
      data=>console.log(data)
    )
  }

  goToDisplayBuilding(id:number)
  {
    this._router.navigate(['/displayBuilding',id]);
  }

  goToMakeBuilding()
  {
    this._router.navigate(['/makeBuilding']);
  }
}

