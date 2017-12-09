import { Component, OnInit } from '@angular/core';
import {SessionDTO, TopicDTO} from "../../models";
import {SessionService} from "../../services/session.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TopicService} from "../../services/topic.service";
import {LoggedUtils} from "../../utils/logged.utils";


@Component({
  moduleId: module.id,
  selector: 'topicPage',
  templateUrl: './topicView.component.html',
  styleUrls: ['./topicView.component.css'],
  providers: [TopicService]

})
export class TopicViewComponent
{
  topics: TopicDTO[];
  description: string;
  topic: TopicDTO;

  constructor(private topicService: TopicService, private route: ActivatedRoute, private _router: Router)
  {
    this.topicService.getTopics(this.route.snapshot.params['p2']).subscribe
    (
      (data: TopicDTO[]) => this.topics = data,
      error => alert(error),
      () => console.log(JSON.stringify(this.topics))
    );
  }

  addTopic()
  {
    var topic = new TopicDTO(null,this.description, LoggedUtils.getUser(),false,0);
    this.topicService.addTopic(this.route.snapshot.params['p2'],topic).subscribe
    (
      (data: TopicDTO) => this.topic = data,
      error => alert(error)
    );

    if(typeof this.topics === 'undefined')
    {
      this.topics = [];
    }
    this.topics.push(topic);
    this.description = '';
  }

}
