import { Component, NgModule, OnInit } from '@angular/core';
import {LoggedUtils} from "../../utils/logged.utils";
import { AuthenticationService } from "../../services/authentication.service";
import { Link } from "../../models";

@Component({
  moduleId: module.id,
  selector: 'navbar',
  templateUrl: 'navbar.component.html'
})
export class NavbarComponent implements OnInit
{

  links: Link[];
  currentRole:string;

  constructor(private authenticationService: AuthenticationService)
  {
    this.links = [];
    //console.log(this.currentRole);
    //authenticationService.getLoggedInName.subscribe((name: string) => this.changeRole(name));
    //authenticationService.getLoggedInName.subscribe((name: string) => console.log('Triggered!', name));
    //authenticationService.getMessage().subscribe(value => this.changeRole(value));
    //authenticationService.ppp()
  }

  ngOnInit() {
    this.authenticationService.getMessage().subscribe((value:string) => this.changeRole(value));
  }

  private changeRole(role: string)
  {
    //console.log("usaooooo1");
    this.currentRole = role;
    //console.log(role)


    if (this.currentRole == "ROLE_ADMIN")
      this.presetAdmin();
    else if (this.currentRole == "TENANT")
      this.presetTenant();
    else if (this.currentRole == "FIRM")
      this.presentFirm();
    else if (this.currentRole == "INSTITUTION")
      this.presentInstitution();
  }

  addLink(link: Link)
  {
    this.links.push(link);
  }

  presetAdmin()
  {
    //this.addLink({text: "Home page", routerLink: "/adminPage"});
    console.log("admin");
  }

  presetTenant()
  {
    console.log("tenant");
  }

  presentFirm()
  {
    console.log("firm");
  }

  presentInstitution()
  {
    console.log("institution");
  }


}
