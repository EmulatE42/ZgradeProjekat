import { Component, OnInit } from '@angular/core';

import { BugDTO,FirmDTO } from "../../../models";
import { InstitutionService } from "../../../services/institution.service";
import { TenantService } from "../../../services/tenant.service";
import { ActivatedRoute, Router }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'setResponsibleFirm',
  templateUrl: './setResponsibleFirm.component.html',
  styleUrls: ['./setResponsibleFirm.component.css'],
  providers: [InstitutionService, TenantService]

})
export class SetResponsibleFirmComponent implements OnInit
{
  firms:FirmDTO[];
  responsibleFirm:FirmDTO;
  bug:BugDTO;

  constructor(private institutionService: InstitutionService, private tenantService:TenantService, private route: ActivatedRoute, private _router: Router)
  {
    this.institutionService.getAllFirms().subscribe
    (
      (data:FirmDTO[]) => {this.firms = data; console.log(this.firms);},
      error => alert(error)
    );
  }

  ngOnInit() {
    this.tenantService.getBug(this.route.snapshot.params['p1']).subscribe
    (
      (data:BugDTO) => this.bug = data
    );
  }

  save()
  {
    this.institutionService.connectBugAndFirm(this.route.snapshot.params['p1'], this.responsibleFirm.id).subscribe
    (
      ()=>this.goToInstitutionBugs()
    );
  }

  goToInstitutionBugs()
  {
    this._router.navigate(['/responsiblePersonBugs']);
  }
}

