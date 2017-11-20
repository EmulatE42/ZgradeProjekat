import { Component } from '@angular/core';

import {BuildingListItemDTO} from "../../models";
import { AdminService } from "../../services/admin.service";

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

  constructor(private adminService: AdminService )
  {
     this.adminService.addAllBuildings().subscribe(
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
}

