import { Component, OnInit } from '@angular/core';

import {BuildingDTO, LocationDTO, BugDTO} from "../../../models";
import { TenantService } from "../../../services/tenant.service";
import { ActivatedRoute, Router }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'showAllBugs',
  templateUrl: './showAllBugs.component.html',
  styleUrls: ['./showAllBugs.component.css'],
  providers: [TenantService]

})
export class ShowAllBugsComponent {

  bugs:BugDTO[];
  locationId:number;

  constructor(private tenantService: TenantService, private route: ActivatedRoute, private _router: Router)
  {
    this.locationId = this.route.snapshot.params['p1'];
    this.tenantService.getAllBugs(this.route.snapshot.params['p1']).subscribe
    (
      (data:BugDTO[]) => this.bugs = data,
      error => alert(error)
    );
  }

  goToAddNewBug()
  {
    this._router.navigate(['/addNewBug/'+this.route.snapshot.params['p1']]);
  }

  deleteBug(index:number)
  {
    this.tenantService.deleteBug(this.bugs[index].id, this.route.snapshot.params['p1']).subscribe
    (
      () => this.removeBug(index)
    );
  }

  removeBug(index:number)
  {
    this.bugs.splice(index,1);
  }
}
