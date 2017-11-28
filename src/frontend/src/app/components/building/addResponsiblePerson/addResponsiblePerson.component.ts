import { Component } from '@angular/core';

import {InstitutionDTO, TenantDTO, ResponsiblePersonDTO} from "../../../models";
import { AdminService } from "../../../services/admin.service";
import { ActivatedRoute }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'addResponsiblePerson',
  templateUrl: './addResponsiblePerson.component.html',
  styleUrls: ['./addResponsiblePerson.component.css'],
  providers: [AdminService]

})
export class AddResponsiblePersonComponent
{
  institutionDTOs:InstitutionDTO[];
  tenants:TenantDTO[];
  type:string;
  description:string;
  selectedTenant:TenantDTO;
  selectedInstitution:InstitutionDTO;
  isTenant:boolean;

  constructor(private adminService: AdminService, private route: ActivatedRoute)
  {
    this.adminService.getAllInstitutions().subscribe
    (
      (data:InstitutionDTO[]) => this.institutionDTOs = data,
      error => alert(error),
      () => this.getAllTenants()
    );
  }

  getAllTenants()
  {
    this.adminService.getAllTenants().subscribe
    (
      (data:TenantDTO[]) => this.tenants = data,
      error => alert(error)
    );
  }

  add()
  {
    if(this.type=="TENANT") this.isTenant=true;
    else this.isTenant=false;
    this.adminService.addResponsiblePerson(new ResponsiblePersonDTO(null,this.selectedTenant,this.selectedInstitution,this.isTenant,this.description),
      this.route.snapshot.params['p1']).subscribe
    (
      (data) => console.log(data),
      error => alert(error)
    );
  }

}
