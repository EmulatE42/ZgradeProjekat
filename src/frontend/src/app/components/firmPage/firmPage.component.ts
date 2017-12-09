import { Component } from '@angular/core';

import { BugDTO } from "../../models";
import { FirmService } from "../../services/firm.service";
import { Router }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'firmPage',
  templateUrl: './firmPage.component.html',
  styleUrls: ['./firmPage.component.css'],
  providers: [FirmService]

})
export class FirmPageComponent
{
  bugs:BugDTO[];

  constructor(private firmService: FirmService, private _router: Router)
  {
    this.firmService.getBugsOfFirm().subscribe(
      (data:BugDTO[]) => this.bugs = data,
    );
  }

}
