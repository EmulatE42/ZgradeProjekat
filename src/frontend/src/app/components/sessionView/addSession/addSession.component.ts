import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TopicDTO, SessionDTO, ParliamentDTO, Parliament} from "../../../models";
import {LoggedUtils} from "../../../utils/logged.utils";
import {SessionService} from "../../../services/session.service";
import {TopicService} from "../../../services/topic.service";
import { FormGroup, FormBuilder } from "@angular/forms";


@Component({
  moduleId: module.id,
  selector: 'sessionPage',
  templateUrl: './addSession.component.html',
  styleUrls: ['./addSession.component.css'],
  providers: [SessionService, TopicService]

})
export class AddSessionComponent
{
  date: string;
  time: string;
  topics: TopicDTO[] = [];
  topic: TopicDTO;
  description: string;
  session: SessionDTO;



  constructor(private sessionService: SessionService, private topicService: TopicService, private route: ActivatedRoute, private _router: Router)
  {

  }


  addSession()
  {
    var parlament = new Parliament(this.route.snapshot.params['p1'], null)
    var d = new Date(this.date + ' ' + this.time);
    this.session = new SessionDTO(null,d,null,null,null,LoggedUtils.getUser(), parlament);
    console.log(JSON.stringify(this.session));


    this.sessionService.addSession(this.session).subscribe
    (
      (data: SessionDTO) => this.session = data,
      error => alert(error),
      () => this.saveTopics()
    );

  }

  addTopic()
  {
    this.topics.push(new TopicDTO(null, this.description, LoggedUtils.getUser(),false,0,0));
    this.description = '';
  }

  deleteTopic(index: any)
  {
    this.topics.splice(index, 1);
  }

  saveTopics()
  {
    for(let topic of this.topics)
    {
      this.topicService.addTopic(this.session.id, topic).subscribe
      (
        (data: TopicDTO) => this.topic = data,
        error => alert(error)
      );
    }


    this._router.navigate(['/parlament/'+this.route.snapshot.params['p1'] + '/sessions'])
  }
}
