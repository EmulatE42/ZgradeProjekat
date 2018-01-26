import { Component, OnInit } from '@angular/core';
import {TenantService} from "../../../services/tenant.service";
import {SurveyService} from "../../../services/survey.service";
import {AnswerDTO, SurveyDTO} from "../../../models";
import {Router} from "@angular/router";

@Component({
  selector: 'app-all-surveys',
  templateUrl: './all-surveys.component.html',
  styleUrls: ['./all-surveys.component.css'],
  providers: [SurveyService]
})
export class AllSurveysComponent implements OnInit {

  surveys:SurveyDTO[] = [];
  ans:AnswerDTO;
  constructor(private surveyService: SurveyService,private _router: Router) {
    this.surveyService.getAllSurveys().subscribe((data: SurveyDTO[]) => this.surveys = data,error => alert(error));

  }

  ngOnInit() {

  }

  popuni(id:number)
  {
    this._router.navigate(['/fillSurvey/'+id]);
  }


}
