import { Component, NgModule, OnInit } from '@angular/core';
import {LoggedUtils} from "../../utils/logged.utils";
import { AuthenticationService } from "../../services/authentication.service";
import { Link } from "../../models";
import { Router }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'navbar',
  templateUrl: 'navbar.component.html'
})
export class NavbarComponent implements OnInit
{

  links: Link[];
  currentRole:string;

  constructor(private authenticationService: AuthenticationService, private _router: Router)
  {
    this.logout();
  }

  ngOnInit() {
    this.authenticationService.getRoleEmitter().subscribe((value:string) => this.changeRole(value));
  }

  private changeRole(role: string)
  {
    this.currentRole = role;
    this.links = [];

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
    this.addLink({text: "Home", routerLink: "/adminPage"});
    this.addLink({text: "Add user", routerLink: "/addUser"});
    this.addLink({text: "About", routerLink: "/about"});
    this.addLink({text: "Logout", routerLink:"/" });
    this._router.navigate(['/adminPage']);

  }

  presetTenant()
  {
    console.log(LoggedUtils.getIsResponsible());
    this.addLink({text: "Home", routerLink: "/tenantPageComponent"});
    this.addLink({text: "Parlaments", routerLink: "/parlaments"});
    if(LoggedUtils.getIsResponsible()==true)
    {
      this.addLink({text: "Your responsibility", routerLink: "/responsiblePersonBugs"});
    }
    this.addLink({text: "Make survey", routerLink: "/makeSurvey"});
    this.addLink({text: "Surveys", routerLink: "/surveys"});
    this.addLink({text: "Public notifications", routerLink: "/publicNotifications"});
    this.addLink({text: "About", routerLink: "/about"});
    this.addLink({text: "Logout", routerLink:"/" });
    this._router.navigate(['/tenantPageComponent']);
  }

  presentFirm()
  {
    this.addLink({text: "Home", routerLink: "/firmPage"});
    this.addLink({text: "About", routerLink: "/about"});
    this.addLink({text: "Logout", routerLink:"/" });
    this._router.navigate(['/firmPage']);
  }

  presentInstitution()
  {
    this.addLink({text: "Home", routerLink: "/responsiblePersonBugs"});
    this.addLink({text: "About", routerLink: "/about"});
    this.addLink({text: "Logout", routerLink:"/" });
    this._router.navigate(['/responsiblePersonBugs']);
  }

  logout()
  {
    this.links = [];
    this.addLink({text: "Login", routerLink: "/"});
    this.addLink({text: "About", routerLink: "/about"});
    LoggedUtils.clearLocalStorage();
  }
}
