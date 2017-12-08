import { Component, OnInit } from '@angular/core';

import { Bill } from "../../../models";
import { TenantService } from "../../../services/tenant.service";
import { ActivatedRoute, Router }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'showBill',
  templateUrl: './showBill.component.html',
  styleUrls: [],
  providers: [TenantService]
})
export class ShowBillComponent {

  bill:Bill;
  ttt:number=0;

  constructor(private tenantService: TenantService, private route: ActivatedRoute, private _router: Router) {
    this.tenantService.getBill(this.route.snapshot.params['p1']).subscribe
    (
      (data:Bill) => this.bill = data,
      error => alert(error)
    );
  }

  payBill()
  {
    this.tenantService.payBill(this.route.snapshot.params['p1']).subscribe
    (
      () => this.goBack(),
    );
  }

  goBack()
  {
    this._router.navigate(['/showAllBugs/'+this.route.snapshot.params['p2']]);
  }
}

