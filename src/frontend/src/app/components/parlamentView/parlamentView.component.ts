import { Component, OnInit } from '@angular/core';
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

  constructor(private parlamentService: ParlamentService)
  {
    this.parlamentService.getParlaments().subscribe
    (
      (data: ParliamentDTO[]) => this.parlaments = data,
      error => alert(error),
      () =>
      {}
    );

  }
}
