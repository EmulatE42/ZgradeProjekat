import { Component, OnInit } from '@angular/core';
import {TenantService} from "../../../services/tenant.service";
import {SurveyService} from "../../../services/survey.service";
import {AnswerDTO, SurveyDTO} from "../../../models";
import {Router} from "@angular/router";
import {LoggedUtils} from "../../../utils/logged.utils";

@Component({
  selector: 'app-all-surveys',
  templateUrl: './all-surveys.component.html',
  styleUrls: ['./all-surveys.component.css'],
  providers: [SurveyService]
})
export class AllSurveysComponent implements OnInit {

  surveys:SurveyDTO[] = [];
  filledSurveys:SurveyDTO[] = [];
  ans:AnswerDTO;
  pocetniDatum : string;
  krajnjiDatum : string;
  constructor(private surveyService: SurveyService,private _router: Router) {
    this.surveyService.getAllSurveys().subscribe((data: SurveyDTO[]) => this.surveys = data,error => alert(error));
    this.surveyService.getAllFilledSurveysByID(LoggedUtils.getId()).subscribe((data: SurveyDTO[]) => this.filledSurveys = data,error => alert(error));
  }

  ngOnInit() {

  }
  pretvoriDatumUString(d: string)
  {
   let t = d.split("-")
    return t[2]+"-" + t[1] + "-" + t[0]
  }

  provera()
  {
    if (this.pocetniDatum && this.krajnjiDatum) {
      if (this.pocetniDatum.length == 10 && this.krajnjiDatum.length == 10) {
        this.surveyService.getAllBetweenDates(this.pocetniDatum, this.krajnjiDatum).subscribe((data: SurveyDTO[]) => this.surveys = data, error => alert(error));

      }
      else {
        this.surveyService.getAllSurveys().subscribe((data: SurveyDTO[]) => this.surveys = data, error => alert(error));

      }
      this.surveyService.getAllFilledSurveysByID(LoggedUtils.getId()).subscribe((data: SurveyDTO[]) => this.filledSurveys = data, error => alert(error));
    }
  }

  public onInput(value: Date): void {
    this.pocetniDatum = this.pretvoriDatumUString(value.toString());
    console.log(this.pocetniDatum.length)
    this.provera()
  }

  public onInput2(value: Date): void {
    this.krajnjiDatum = this.pretvoriDatumUString(value.toString());
    console.log(this.krajnjiDatum.length)
    this.provera()
  }
  nalazi(id:number)
  {
    for (let i = 0 ; i < this.filledSurveys.length;i++)

    {
      if (id==this.filledSurveys[i].id)
        return true;
    }
    return false;
  }
  popuni(id:number)
  {
    this._router.navigate(['/fillSurvey/'+id]);
  }


}
