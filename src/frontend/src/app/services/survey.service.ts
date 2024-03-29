import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {AnswerDTO, FilledSurveyDTO, QuestionDTO, SurveyDTO} from "../models";

const httpOptions = {
  headers: new HttpHeaders({ 'content-type': 'application/json' })
};

@Injectable()
export class SurveyService {

  constructor(private http: HttpClient) {
  }

  addSurvey(survey: SurveyDTO) {
    var param = JSON.stringify(survey);
    return this.http.post("http://localhost:8080/survey/addSurvey", param, httpOptions);
  }

  getAllSurveys() {
    return this.http.get("http://localhost:8080/survey/getAllSurveys")
  }

  getSurveyById(param:number)
  {
    return this.http.get("http://localhost:8080/survey/getSurveyById/"+param)
  }
  addAnswer(answer:AnswerDTO)
  {
    var param = JSON.stringify(answer);
    return this.http.post("http://localhost:8080/survey/addAnswer", param, httpOptions);
  }
  updateAnswer(a:number,id:number)
  {
    let answer = a+";"+id;
    //alert("US  " + answer);
    var param = JSON.stringify(answer);
    return this.http.put("http://localhost:8080/survey/updateAnswer", param, httpOptions);
  }
  addFilledSurvey(fs :FilledSurveyDTO)
  {
    var param = JSON.stringify(fs);
    return this.http.post("http://localhost:8080/survey/addFilledSurvey", param, httpOptions);
  }

  getAllFilledSurveysByID(id:number) {
    return this.http.get("http://localhost:8080/survey/getAllFilledSurveys/"+id)
  }

  getAllBetweenDates(d1:string,d2:string)
  {
    let d = d1+";"+d2;
    var param = JSON.stringify(d);
    return this.http.post("http://localhost:8080/survey/getSurveyBetweenDates", param,httpOptions);
  }


}
