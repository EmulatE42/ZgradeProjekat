import { Component } from '@angular/core';

import {Bill, BillItem} from "../../../models";
import { Router, ActivatedRoute }    from '@angular/router';
import { FirmService } from "../../../services/firm.service";

@Component({
  moduleId: module.id,
  selector: 'bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css'],
  providers: [FirmService]

})
export class BillComponent
{
  billItem:BillItem;
  bill:Bill;
  ttt:number=0;

  constructor(private route: ActivatedRoute, private _router: Router,private firmService:FirmService)
  {
    this.billItem = new BillItem(null,"","",0,0);
    this.bill = new Bill(null,[],null);
  }

  remove(index:number)
  {
    this.ttt = this.ttt - (this.bill.billItemSet[index].amount * this.bill.billItemSet[index].quantity);
    this.bill.billItemSet.splice(index,1);
  }

  add()
  {
    this.ttt = this.ttt + this.billItem.amount * this.billItem.quantity;
    this.bill.billItemSet.push(new BillItem(null, this.billItem.name, this.billItem.description, this.billItem.quantity, this.billItem.amount));
    this.billItem.amount=0;
    this.billItem.description="";
    this.billItem.name=""
    this.billItem.quantity=0;
  }

  createBill()
  {
    this.firmService.makeBill(this.route.snapshot.params['p1'],this.bill).subscribe
    (
      data => console.log(data)
    );
  }

}
