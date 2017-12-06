import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TopicDTO, SessionDTO, ParliamentDTO} from "../../../models";
import {LoggedUtils} from "../../../utils/logged.utils";
import {SessionService} from "../../../services/session.service";


@Component({
  moduleId: module.id,
  selector: 'sessionPage',
  templateUrl: './addSession.component.html',
  styleUrls: ['./addSession.component.css'],
  providers: [SessionService]

})
export class AddSessionComponent
{
  date: Date;
  topics: TopicDTO[] = [];
  description: string;
  session: SessionDTO;

  constructor(private sessionService: SessionService, private route: ActivatedRoute, private _router: Router)
  {

  }

  addSession()
  {
    this.session = new SessionDTO(null,this.date,this.topics,null,null,LoggedUtils.getUser(), new ParliamentDTO(this.route.snapshot.params['p1'], null, null));
    var nesto = new ParliamentDTO(null,null,null)
    this.sessionService.addSession(this.session).subscribe
    (
      (data: SessionDTO) => this.session = data,
      error => alert(error),
      () => this._router.navigate(['/parlament/'+this.route.snapshot.params['p1'] + '/sessions'])
    );

  }

  addTopic()
  {
    this.topics.push(new TopicDTO(null, this.description, LoggedUtils.getUser(),false,0,null));
    this.description = '';
  }

  deleteTopic(index: any)
  {
    this.topics.splice(index, 1);
  }
}
