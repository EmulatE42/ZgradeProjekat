import { Component, OnInit } from '@angular/core';

import {BuildingDTO, LocationDTO} from "../../../models";
import { AdminService } from "../../../services/admin.service";
import { ActivatedRoute, Router }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'addLocation',
  templateUrl: './addLocation.component.html',
  styleUrls: ['./addLocation.component.css'],
  providers: [AdminService]

})
export class AddLocationComponent implements OnInit
{

  type:string;
  floor:number;
  square:number;
  numberOfFloors:number;
  buildingId:number;


  constructor(private adminService: AdminService, private route: ActivatedRoute, private _router: Router )
  {
  }

  ngOnInit() {
    this.buildingId=this.route.snapshot.params['p1'];
  }

  addLocation()
  {
    this.adminService.addLocation(new LocationDTO(null,this.type,this.buildingId,
      this.floor,this.square,this.numberOfFloors, null)).subscribe(
      ()=>this.goToDisplayBuilding()
    );
  }

  goToDisplayBuilding()
  {

    this._router.navigate(['/displayBuilding/'+this.buildingId]);
  }
}

