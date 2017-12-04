import { Component, OnInit } from '@angular/core';
import {SessionDTO} from "../../models";
import {SessionService} from "../../services/session.service";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  moduleId: module.id,
  selector: 'sessionPage',
  templateUrl: './sessionView.component.html',
  styleUrls: ['./sessionView.component.css'],
  providers: [SessionService]

})
export class SessionViewComponent
{
  sessions: SessionDTO[];

  constructor(private sessionService: SessionService, private route: ActivatedRoute, private _router: Router)
  {
    this.sessionService.getSessions(this.route.snapshot.params['p1']).subscribe
    (
      (data: SessionDTO[]) => this.sessions = data,
      error => alert(error)
    );
  }
}
