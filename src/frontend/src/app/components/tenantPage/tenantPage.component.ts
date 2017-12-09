import { Component, OnInit } from '@angular/core';
import { TenantService } from "../../services/tenant.service";
import {LocationDTO, BuildingDTO, TenantDTO} from "../../models";

import { ActivatedRoute }    from '@angular/router';

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

  constructor(private tenantService: TenantService)
  {
    this.tenantService.getApartmentsOfTenant().subscribe
    (
      (data:LocationDTO[]) => this.locations = data,
      error => alert(error)
    );
  }
}
