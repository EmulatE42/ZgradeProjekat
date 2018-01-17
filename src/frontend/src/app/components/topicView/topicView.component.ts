import { Component, OnInit } from '@angular/core';
import {SessionDTO, TopicDTO, VoteDTO} from "../../models";
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
  vote: VoteDTO;

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
    var topic = new TopicDTO(null,this.description, LoggedUtils.getUser(),false,0,0);
    this.topicService.addTopic(this.route.snapshot.params['p2'],topic).subscribe
    (
      (data: TopicDTO) => this.topic = data,
      error => alert(error),
      () => this.complete()
    );

  }

  complete()
  {
    if(typeof this.topics === 'undefined')
    {
      this.topics = [];
    }

    this.topics.push(this.topic);
    this.description = '';
  }

  like(id: number)
  {
    this.topic = this.findTopicById(id);

    this.topicService.getVote(this.topic).subscribe(response => {

      // You can access status:
      if(response.status == 204)
        this.completeLike();
      else
      {
        toastr.info('You have already voted!', 'Information', {timeOut: 5000})
      }
      // Or any other header:
      // console.log(response.headers.get('X-Custom-Header'));
    });
  }

  completeLike()
  {

      this.topic.pos_votes += 1;

      this.topicService.updatePositiveVote(this.topic).subscribe
      (
        (data: TopicDTO) => this.topic = data,
        error => alert(error)
      );
  }

  dislike(id: number)
  {
    this.topic = this.findTopicById(id);

    this.topicService.getVote(this.topic).subscribe(response => {

      // You can access status:
      if (response.status == 204)
        this.completeDislike();
      else
      {
        toastr.info('You have already voted!', 'Information', {timeOut: 5000})
      }
    });
  }

  completeDislike()
  {

      this.topic.neg_votes += 1;

      this.topicService.updateNegativeVote(this.topic).subscribe
      (
        (data: TopicDTO) => this.topic = data,
        error => alert(error)
      );
  }

  findTopicById(id: number): TopicDTO
  {
    for(let topic of this.topics)
    {
      if(topic.id == id)
        return topic;
    }
    alert('Vraca null');
    return null;
  }

}
