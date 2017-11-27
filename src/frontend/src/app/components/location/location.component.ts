import { Component, OnInit } from '@angular/core';
import { AdminService } from "../../services/admin.service";
import {LocationDTO, BuildingDTO, TenantDTO} from "../../models";

import { ActivatedRoute }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css'],
  providers: [AdminService]

})
export class LocationComponent implements OnInit
{
  location:LocationDTO;
  tenants:TenantDTO[];
  selectedTenant:TenantDTO;

  constructor(private adminService: AdminService, private route: ActivatedRoute)
  {
    this.adminService.getAllTenants().subscribe
    (
      (data:TenantDTO[]) => this.tenants = data,
      error => alert(error)
    );
  }

  ngOnInit() {
    this.adminService.getLocationById(this.route.snapshot.params['p1']).subscribe
    (
      (data:LocationDTO) => this.location = data,
      error => alert(error),
      ()=>this.setSelectedTenant()
    );
  }

  setSelectedTenant()
  {
    if(this.location.tenantDTO!=null)
    {
      this.selectedTenant = this.location.tenantDTO;
    }
  }

  connect()
  {
    console.log(this.selectedTenant.username);

    this.adminService.connectTenantAndApartment(this.location.locationId,this.selectedTenant.id).subscribe
    (
      (data) => console.log(data),
      error => alert(error)
    );
  }

}

