import { Component } from '@angular/core';
import { AuthenticationService } from "../../services/authentication.service";

@Component({
  moduleId: module.id,
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [AuthenticationService]

})
export class HomeComponent
{
  private usr: string;
  private password: string;

  constructor(private authenticationService: AuthenticationService)
  {
    this.usr = "";
    this.password = "";
  }

  authenticate()
  {

    this.authenticationService.authenticateUser(this.usr, this.password).subscribe(
      data => localStorage.setItem("loggedUser", JSON.stringify(data)),
      error => alert("Incorrect username and/or password"),
      () => console.log(JSON.parse(localStorage.getItem("loggedUser")))
    );

  }

}