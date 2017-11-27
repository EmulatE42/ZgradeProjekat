import { Component, OnInit } from '@angular/core';

import {BuildingDTO, LocationDTO} from "../../../models";
import { AdminService } from "../../../services/admin.service";
import { ActivatedRoute }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'displayBuilding',
  templateUrl: './displayBuilding.component.html',
  styleUrls: ['./displayBuilding.component.css'],
  providers: [AdminService]

})
export class DisplayBuildingComponent implements OnInit
{

  building:BuildingDTO=null;


  constructor(private adminService: AdminService, private route: ActivatedRoute)
  {
  }

  initLocations()
  {
    if(this.building.locations==null)
    {
      this.building.locations=[];
    }
  }

  ngOnInit() {
    this.adminService.getBuildingById(this.route.snapshot.params['p1']).subscribe
    (
      (data:BuildingDTO) => this.building = data,
      error => alert(error),
      ()=>this.initLocations()
    );
  }

  deleteLocation(locationId:number,index:number)
  {
    this.building.locations.splice(index,1);
    this.adminService.deleteLocation(locationId).subscribe
    (
      data=>console.log(data)
    );
  }

}

