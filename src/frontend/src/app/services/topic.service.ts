import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

import {LoggedUtils} from "../utils/logged.utils";
import {TopicDTO} from "../models";

@Injectable()
export class TopicService {

  constructor(private http: Http) {}

  getTopics(id: number) {
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    return this.http.get("http://localhost:8080/topic/getTopicsBySessionId/" + id, {headers: headers})
      .map(res => res.json());
  }

  addTopic(sessionID:number, topic: TopicDTO) {
    var param = JSON.stringify(topic);
    var headers = new Headers();
    console.log("Token:   " + LoggedUtils.getToken());
    headers.append("X-Auth-Token", LoggedUtils.getToken());
    headers.append('Content-Type', 'application/json');
    return this.http.post("http://localhost:8080/topic/addBySessionId/" + sessionID, param, {headers: headers})
      .map(res => res.json());
  }

  getId() {
    return LoggedUtils.getId();
  }

  getUsername()
  {
    return LoggedUtils.getUsername();
  }


}
