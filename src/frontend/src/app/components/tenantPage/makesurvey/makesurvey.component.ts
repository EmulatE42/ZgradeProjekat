import { Component, OnInit } from '@angular/core';
import {QuestionDTO} from "../../../models";








@Component({
  selector: 'makesurvey',
  templateUrl: './makesurvey.component.html',
  styleUrls: ['./makesurvey.component.css']
})

export class MakesurveyComponent implements OnInit {

  showDialog = false;
  questionsTable:QuestionDTO[] = [];
  choicesTable :String[][] = []
  types:string[] = ["Text","Rate","Multiple"]
  odabir:number = -1;
  constructor() {
  }

  ngOnInit() {

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
    let duzina = this.questionsTable.length;
    this.questionsTable.push(new QuestionDTO(duzina,null,null,false,false,null));
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

}

/*
 public id: number,
 public text: string,
 public answers: AnswerDTO[],
 public secondType: boolean,
 public thirdType: boolean,
 public choices: string
* */
