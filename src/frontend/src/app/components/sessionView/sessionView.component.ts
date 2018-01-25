import { Component, OnInit } from '@angular/core';
import {SessionDTO, TopicDTO} from "../../models";
import {SessionService} from "../../services/session.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";


import * as jsPDF from "jspdf";
import {TopicService} from "../../services/topic.service";


@Component({
  moduleId: module.id,
  selector: 'sessionPage',
  templateUrl: './sessionView.component.html',
  styleUrls: ['./sessionView.component.css'],
  providers: [SessionService, TopicService]

})
export class SessionViewComponent
{
  sessions: SessionDTO[];
  topics: TopicDTO[];
  building_manager: boolean;
  session: SessionDTO;
  sessionID: number;
  record: string;

  constructor(private sessionService: SessionService, private topicService: TopicService,  private route: ActivatedRoute, private _router: Router)
  {
    this.sessionService.isBuildingManager().subscribe
    (
      response => {

        if(response.status == 200)
          this.building_manager = true;
        else
          this.building_manager = false;
      },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          console.log("Client-side error occured.");
        } else {
          console.log("Server-side error occured.");
        }

        if(err.status == 403)
          this.complote();
      },
      () =>
      {
        this.complote();
      }
    );


  }

  complote()
  {
    this.sessionService.getSessions(this.route.snapshot.params['p1']).subscribe
    (
      (data: SessionDTO[]) => this.sessions = data,
      error => alert(error)
    );
  }


  goToAddNewSession()
  {
    this._router.navigate(['/parlament/'+this.route.snapshot.params['p1'] + '/add_session']);
  }

  goToListOfTopics(id: number)
  {
    // this.topicService.getTopics(id).subscribe
    // (
    //   (data: TopicDTO[]) => this.topics = data,
    //   error => alert(error),
    //   () => this.write()
    // );

    this.sessionService.getSession(id).subscribe
    (
      (date: SessionDTO) => this.session = date,
      error => alert(error),
      () =>
      {
        if(new Date(this.session.date) > new Date())
        {
          this._router.navigate(['/parlament/'+this.route.snapshot.params['p1'] + '/session/' + id + '/topics']);
        }
        else
        {
          this.topicService.getTopics(id).subscribe
          (
            (data: TopicDTO[]) => this.topics = data,
            error => alert(error),
            () => this.write()
          );
        }
      }
    );


    // this._router.navigate(['/parlament/'+this.route.snapshot.params['p1'] + '/session/' + id + '/topics']);
  }

  write()
  {
    let html: string;
    html = "<h2 align=\'left\'> Dnevni red sednice odrzane " + new Date(this.session.date).toLocaleString() + "  </h2> ";
    let nmb = 1;
    for(let i = 0; i < this.topics.length; i++)
    {
      if(this.topics[i].pos_votes - this.topics[i].neg_votes >= 0)
        html += nmb++ + '. ' + this.topics[i].description + '<br>';
    }

    const doc = new jsPDF();

    let specialElementHandlers = {

      '#editor': function(element, renderer)
      {
        return true;
      }
    }

    doc.fromHTML(html, 15, 15,
      {
        'width': 190,
        'elementHandlers': specialElementHandlers
      }
    );

    doc.save("topics.pdf");
  }

  isPassedSession(id)
  {
    for(let i = 0; i < this.sessions.length; i++)
    {
      if(this.sessions[i].id == id) {
        this.session = this.sessions[i];
        break;
      }
    }

    if(new Date(this.session.date) < new Date())
      return true;
    else
      return false;

  }

  uploadRecord()
  {
    for(let i = 0; i < this.sessions.length; i++)
    {
      if(this.sessions[i].id == this.sessionID) {
        this.session = this.sessions[i];
        break;
      }
    }

    this.session.record = this.record;
  }

  initSessionID(id)
  {
    this.sessionID = id;
  }





}
