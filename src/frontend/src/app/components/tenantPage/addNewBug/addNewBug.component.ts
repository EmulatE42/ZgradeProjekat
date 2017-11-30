import { Component} from '@angular/core';

import {BuildingDTO, ResponsiblePersonDTO, BugDTO} from "../../../models";
import { TenantService } from "../../../services/tenant.service";
import { AdminService } from "../../../services/admin.service";
import { ActivatedRoute, Router }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'addNewBug',
  templateUrl: './addNewBug.component.html',
  styleUrls: ['./addNewBug.component.css'],
  providers: [TenantService,AdminService]

})
export class AddNewBugComponent {

  responsiblePeople:ResponsiblePersonDTO[];
  selectedResponsiblePerson:ResponsiblePersonDTO;
  description:string;

  constructor(private tenantService: TenantService, private adminService: AdminService,
              private route: ActivatedRoute, private _router: Router)
  {
    this.adminService.getAllResponsiblePersonsByLocationId(this.route.snapshot.params['p1']).subscribe
    (
      (data:ResponsiblePersonDTO[]) => this.responsiblePeople = data,
      error => alert(error)
    );
  }

  add()
  {
    this.tenantService.addNewBug(this.route.snapshot.params['p1'], new BugDTO(null, this.description,new Date(),null,false,this.selectedResponsiblePerson)).subscribe
    (
      () => this.goBack()
    );
  }

  goBack()
  {
    this._router.navigate(['/showAllBugs/'+this.route.snapshot.params['p1']]);
  }

}

