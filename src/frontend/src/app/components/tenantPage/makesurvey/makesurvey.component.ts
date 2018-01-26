import { Component, OnInit } from '@angular/core';
import {AnswerDTO, LocationDTO, QuestionDTO, SurveyDTO} from "../../../models";
import {TenantService} from "../../../services/tenant.service";
import {SurveyService} from "../../../services/survey.service";
import {element} from "protractor";
import {Router} from "@angular/router";

@Component({
  selector: 'makesurvey',
  templateUrl: './makesurvey.component.html',
  styleUrls: ['./makesurvey.component.css'],
  providers: [TenantService,SurveyService]
})

export class MakesurveyComponent implements OnInit {

  showDialog = false;
  questionsTable:QuestionDTO[] = [];
  choicesTable :String[][] = []
  types:string[] = ["Text","Rate","Multiple"]
  odabir:number = -1;
  datum:string = "";
  zgrade:number[] = []
  locations:LocationDTO[];

  constructor(private tenantService: TenantService,private surveyService: SurveyService,private _router: Router)
  {
    this.tenantService.getApartmentsOfTenant().subscribe
    (
      (data:LocationDTO[]) => this.locations = data,
      error => alert(error),() => this.read()
    );
  }

  read()
  {
    if (this.locations.length > 0) {

      for ( let i = 0 ; i< this.locations.length; i++)
       {
       this.zgrade.push(this.locations[i].buildingId);
       }

    }

  }

  ngOnInit() {
    var date = new Date();
    this.datum = ('0' + date.getDate()).slice(-2) + '/' + ('0' + (date.getMonth() + 1)).slice(-2) + '/' + date.getFullYear();
    //alert( this.datum );


  }


  submituj(j:number,vrednost:string)
  {

   var el =  document.getElementsByClassName('h')  as HTMLCollectionOf<HTMLElement>;
   var el1 =  document.getElementsByClassName('ha1')  as HTMLCollectionOf<HTMLElement>;
   for (let i = 0; i < el.length; i++)
   {
      if (i == j) {
        el[i].style.display = vrednost;
        el1[i].style.display = 'block';
      }
   }
  }
  onChangeType(indeks:number,sta:string)
  {
    if (sta == "Multiple")
    {
      document.getElementById("hed").style.display = 'table-cell';
      this.questionsTable[indeks].thirdType = true;
      this.questionsTable[indeks].secondType = false;
      this.submituj(indeks,'table-cell')
    }
    else {
      if (sta == "Rate")
      {
        this.questionsTable[indeks].secondType = true;
        this.questionsTable[indeks].thirdType = false;
      }
      else
      {
        this.questionsTable[indeks].secondType = false;
        this.questionsTable[indeks].thirdType = false;
      }
      this.submituj(indeks,'none');
      let skloniHeder:boolean  = true;
      for (var i = 0 ; i < this.questionsTable.length;i++)
      {
        if (this.questionsTable[i].thirdType)
          skloniHeder = false;
      }
      if (skloniHeder)
      {
        document.getElementById("hed").style.display = 'none';
      }
    }
  }


  dodajNovi()
  {

    this.questionsTable.push(new QuestionDTO(null,null,[],false,false,"",1));
    this.choicesTable.push([]);


  }
  dodajOdabir(novi : string)
  {

    for (let i = 0 ; i < this.choicesTable.length;i++)
    {
      if (i == this.odabir) {
        if (novi.length !=0) {
          this.choicesTable[i].push(novi);
          this.questionsTable[i].choices += novi + ",";
          var el =  document.getElementsByClassName('novi')  as HTMLCollectionOf<HTMLInputElement>;
          for (let i = 0; i < el.length; i++)
          {

              el[i].value = "";
          }

        }
      }
    }

  }

  otvoriDialog(gde :number)
  {
    this.odabir = gde;
    this.showDialog = !this.showDialog;
  }

  onChangeText(gde:number,sta:string)
  {
      this.questionsTable[gde].text = sta;
  }

  gotovaAnketa(naslov:string) {
    if (naslov.length == 0 || this.questionsTable.length == 0) {
      return;
    }


    for (let i = 0; i < this.choicesTable.length; i++) {
      let duzina = this.questionsTable[i].choices.length
      if (this.questionsTable[i].text == null)
        return;
      if (duzina > 0)
        this.questionsTable[i].choices = this.questionsTable[i].choices.slice(0, duzina - 1);
    }

    for (let i = 0; i < this.zgrade.length; i++) {
      let s = new SurveyDTO(null, naslov, this.datum, this.zgrade[i], this.questionsTable);
      if (i == this.zgrade.length - 1) {
        this.surveyService.addSurvey(s).subscribe(() => this.redirektuj(s));
      }
      else {
        this.surveyService.addSurvey(s).subscribe();
      }
    }
  }
    redirektuj(s:SurveyDTO)
    {
      this._router.navigate(['/tenantPageComponent']);
    }



}

