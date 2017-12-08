import { Component } from '@angular/core';

import {BuildingDTO, LocationDTO, ResponsiblePersonDTO} from "../../../models";
import { AdminService } from "../../../services/admin.service";
import { ActivatedRoute }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'showAllResponsiblePersons',
  templateUrl: './showAllResponsiblePersons.component.html',
  styleUrls: ['./showAllResponsiblePersons.component.css'],
  providers: [AdminService]

})
export class ShowAllResponsiblePersonsComponent
{
  responsiblePeople:ResponsiblePersonDTO[];

  constructor(private adminService: AdminService, private route: ActivatedRoute)
  {
    this.adminService.getAllResponsiblePeople(this.route.snapshot.params['p1']).subscribe
    (
      (data:ResponsiblePersonDTO[]) => this.responsiblePeople = data,
      error => alert(error)
    );
  }


  delete(id:number,index:number)
  {
    this.responsiblePeople.splice(index,1);
    this.adminService.deleteResponsePerson(id, this.route.snapshot.params['p1']).subscribe
    (
      error => alert(error)
    );
  }

}
