import { Injectable } from '@angular/core';
import {Response} from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do'
import 'rxjs/add/operator/catch'
import {LoggedUtils} from "../utils/logged.utils";
import {TopicDTO, VoteDTO} from "../models";

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};


@Injectable()
export class TopicService {

  constructor(private http: HttpClient) {}

  getTopics(id: number) {
    return this.http.get("http://localhost:8080/topic/getTopicsBySessionId/" + id);
  }

  addTopic(sessionID:number, topic: TopicDTO) {
    var param = JSON.stringify(topic);
    return this.http.post("http://localhost:8080/topic/addBySessionId/" + sessionID, param, httpOptions);
  }

  updatePositiveVote(topic: TopicDTO) {
    var param = JSON.stringify(topic);
    return this.http.put("http://localhost:8080/topic/positiveVote/" + this.getId(), param, httpOptions);
  }

  updateNegativeVote(topic: TopicDTO) {
    var param = JSON.stringify(topic);
    return this.http.put("http://localhost:8080/topic/negativeVote/"  + this.getId(), param, httpOptions);
  }

  getVote(topic: TopicDTO)
  {
    return this.http.get("http://localhost:8080/topic/getVote/" + this.getId() + "/" + topic.id, {observe: 'response'});

      // .map(this.extractVote)
      // .do(data => console.log(""))
      // .catch(this.handleError);
  }

  getId() {
    return LoggedUtils.getId();
  }

  getUsername()
  {
    return LoggedUtils.getUsername();
  }

  private extractVote(res: Response) {

    alert('Status: ' + res.status);
    if(res.status == 404)
      return null;
    else {
      let body = res.json();
      return body;
    }
  }

  private handleError(error: Response)
  {
    return Observable.throw(error.json().error || 'Server error');
  }


}
