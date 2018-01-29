import { Component, OnInit } from '@angular/core';
import {PublicNotificationDTO} from "../../../models";
import {PublicNotificationService} from "../../../services/public-notification.service";
import {LoggedUtils} from "../../../utils/logged.utils";

@Component({
  selector: 'app-public-notification',
  templateUrl: './public-notification.component.html',
  styleUrls: ['./public-notification.component.css'],
  providers: [PublicNotificationService]
})
export class PublicNotificationComponent implements OnInit {

  notifications:PublicNotificationDTO[] = [];

  constructor(private publicNotificationService: PublicNotificationService) {
    this.publicNotificationService.getAllPublicNotifications().subscribe
    (
      (data:PublicNotificationDTO[]) => this.notifications = data,
      error => alert(error)
    );
  }

  ngOnInit() {
  }

  pretvoriDatumUString(d: string)
  {
    let t = d.split("/")
    return t[0]+"/" + t[1] + "/" + t[2]
  }

  dodao(opis:string)
  {
    if (opis.length == 0)
    {
      return;
    }
    var date = new Date();
    let datum = ('0' + date.getDate()).slice(-2) + '/' + ('0' + (date.getMonth() + 1)).slice(-2) + '/' + date.getFullYear();
    datum = this.pretvoriDatumUString(datum);
    this.publicNotificationService.addPublicNotification(  new PublicNotificationDTO(null,datum,LoggedUtils.getUsername(),opis) ).subscribe(()=>this.osvezi());
  }
  osvezi()
  {
    this.publicNotificationService.getAllPublicNotifications().subscribe
    (
      (data:PublicNotificationDTO[]) => this.notifications = data,
      error => alert(error)
    );
  }
}
