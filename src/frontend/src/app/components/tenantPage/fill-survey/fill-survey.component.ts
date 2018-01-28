import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AnswerDTO, FilledSurveyDTO, QuestionDTO, SurveyDTO} from "../../../models";
import {SurveyService} from "../../../services/survey.service";
import {LoggedUtils} from "../../../utils/logged.utils";

@Component({
  selector: 'app-fill-survey',
  templateUrl: './fill-survey.component.html',
  styleUrls: ['./fill-survey.component.css'],
  providers: [SurveyService]
})
export class FillSurveyComponent implements OnInit {


  questions: QuestionDTO[] = [];
  answers : AnswerDTO[] = [];
  ans : AnswerDTO;
  id:number;
  survey: SurveyDTO;
  ocene : string [] = ["1","2","3","4","5"];
  constructor(private route: ActivatedRoute, private surveyService : SurveyService,private _router: Router) {
    this.id = +this.route.snapshot.params["p1"];
    this.surveyService.getSurveyById(this.id).subscribe((data: SurveyDTO) => this.survey = data,error => alert(error), () => this.gotov());

  }

  ngOnInit() {

  }

  napraviListu(str:string)
  {
    return str.split(",");
  }

  onChangeTa(gde:number,sta:string)
  {
    //alert(gde + " si promenio " + sta);
    this.answers[gde].text = sta;
  }

  tip(question : QuestionDTO)
  {
    if (question.secondType) return 2;
    else if (question.thirdType) return 3;
    return 1;
  }
  gotov()
  {
    for (let i = 0 ; i < this.survey.questions.length;i++)
    {

      this.questions.push(this.survey.questions[i])
      if (this.tip(this.questions[i]) == 2)
      {
        this.answers.push(new AnswerDTO(null,"",1,""));
      }
      else if (this.tip(this.questions[i]) == 3){
        this.answers.push(new AnswerDTO(null,"",0,this.napraviListu(this.survey.questions[i].choices)[0]));
      }
      else {
        this.answers.push(new AnswerDTO(null,"",0,""));
      }

    }

  }

  onChangeM(gde:number,sta:string)
  {
    this.answers[gde].rate = +sta;
  }

  onChangeC(gde:number,sta:string)
  {
    this.answers[gde].choiced = sta;
  }

  popunjenaAnketa()
  {
    for (let i = 0; i < this.answers.length;i++)
    {
      if (this.tip(this.questions[i]) == 1 && this.answers[i].text.length == 0)
      {
        return;
      }
    }
    for (let i = 0; i < this.answers.length;i++)
    {
      if (i == this.answers.length-1)
      {
        this.surveyService.addAnswer(this.answers[i]).subscribe((data: AnswerDTO) => this.ans = data, error => alert(error), () => this.zavrsi(this.ans.id));

      }
      else
      {
        this.surveyService.addAnswer(this.answers[i]).subscribe((data: AnswerDTO) => this.ans = data, error => alert(error), () => this.updateWithID(this.ans.id));
      }
    }
  }

  updateWithID(idAnswera:number)
  {

    this.surveyService.updateAnswer(this.id,idAnswera).subscribe();
  }

  zavrsi(idAnswera:number)
  {

    this.surveyService.updateAnswer(this.id,idAnswera).subscribe(()=>this.upisiPopunjeno());
  }
  upisiPopunjeno()
  {
    this.surveyService.addFilledSurvey(new FilledSurveyDTO(null,this.id,LoggedUtils.getId())).subscribe(()=>this.redirektuj());
  }
  redirektuj()
  {
    this._router.navigate(['/surveys']);
  }
}
