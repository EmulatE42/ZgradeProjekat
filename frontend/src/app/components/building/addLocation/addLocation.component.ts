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
  flor:number;
  square:number;
  numberOfFlors:number;
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
      this.flor,this.square,this.numberOfFlors, null)).subscribe(
      ()=>this.goToDispayBuilding()
    );
  }

  goToDispayBuilding()
  {

    this._router.navigate(['/displayBuilding/'+this.buildingId]);
  }
}

