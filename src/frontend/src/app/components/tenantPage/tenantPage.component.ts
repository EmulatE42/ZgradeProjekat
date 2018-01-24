import { Component } from '@angular/core';
import { TenantService } from "../../services/tenant.service";
import { LocationDTO } from "../../models";
import { Router }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'tenantPage',
  templateUrl: './tenantPage.component.html',
  styleUrls: ['./tenantPage.component.css'],
  providers: [TenantService]

})
export class TenantPageComponent
{
  locations:LocationDTO[];

  constructor(private tenantService: TenantService, private _router: Router)
  {
    this.tenantService.getApartmentsOfTenant().subscribe
    (
      (data:LocationDTO[]) => this.locations = data,
      error => alert(error)
    );
  }

  goToShowAllBugs(locationId:number)
  {
    this._router.navigate(['/showAllBugs',locationId]);
  }
}
