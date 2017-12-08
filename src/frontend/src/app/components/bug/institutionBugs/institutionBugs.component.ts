import { Component } from '@angular/core';

import { BugDTO } from "../../../models";
import { InstitutionService } from "../../../services/institution.service";
import { ActivatedRoute }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'institutionBugs.',
  templateUrl: './institutionBugs.component.html',
  styleUrls: ['./institutionBugs.component.css'],
  providers: [InstitutionService]

})
export class InstitutionBugsComponent
{
  bugs:BugDTO[];

  constructor(private institutionService: InstitutionService, private route: ActivatedRoute)
  {
    this.institutionService.getBugsOfResponsiblePerson().subscribe
    (
      (data:BugDTO[]) => {this.bugs = data; console.log(this.bugs);},
      error => alert(error)
    );
  }


}
