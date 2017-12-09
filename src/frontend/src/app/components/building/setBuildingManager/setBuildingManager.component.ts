import { Component } from '@angular/core';

import {BuildingDTO, TenantDTO} from "../../../models";
import { AdminService } from "../../../services/admin.service";
import { ActivatedRoute }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'setBuildingManager',
  templateUrl: './setBuildingManager.component.html',
  styleUrls: ['./setBuildingManager.component.css'],
  providers: [AdminService]

})
export class SetBuildingManagerComponent
{
  building:BuildingDTO;
  tenants:TenantDTO[];
  newBuildingManager:TenantDTO;

  constructor(private adminService: AdminService, private route: ActivatedRoute)
  {
    this.adminService.getBuildingById(this.route.snapshot.params['p1']).subscribe
    (
      (data:BuildingDTO) => this.building = data,
      error => alert(error),
      ()=>this.getAllTenantsFromBuilding()
    );
  }

  getAllTenantsFromBuilding()
  {
    this.adminService.getAllTenantsFromBuilding(this.route.snapshot.params['p1']).subscribe
    (
      (data:TenantDTO[]) => this.tenants = data
    );
  }

  save()
  {
    this.building.buildingManager = this.newBuildingManager;
    this.adminService.setBuildingManager(this.route.snapshot.params['p1'], this.newBuildingManager.id).subscribe
    (
      data => console.log(data)
    );
  }

}
