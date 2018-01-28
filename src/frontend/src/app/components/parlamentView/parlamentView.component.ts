import { Component, OnInit } from '@angular/core';
import { Router }    from '@angular/router';
import { ParlamentService } from "../../services/parlament.service";
import {SessionDTO, ParliamentDTO} from "../../models";


@Component({
  moduleId: module.id,
  selector: 'parlamentPage',
  templateUrl: './parlamentView.component.html',
  styleUrls: ['./parlamentView.component.css'],
  providers: [ParlamentService]

})
export class ParlamentViewComponent
{
  parlaments: ParliamentDTO[];

  constructor(private parlamentService: ParlamentService, private _router: Router)
  {
    this.parlamentService.getParlaments().subscribe
    (
      (data: ParliamentDTO[]) => this.parlaments = data,
      error => alert(error),
      () =>
      {}
    );

  }

  goToLink(id: number)
  {
    this._router.navigate(['/parlament',id,'sessions']);
  }
}
