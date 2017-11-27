import { Component } from '@angular/core';
import { AdminService } from "../../services/admin.service";

@Component({
  moduleId: module.id,
  selector: 'addUser',
  styleUrls: ['./addUser.component.css'],
  templateUrl: './addUser.component.html',
  providers: [AdminService]
})

export class AddUserComponent  {
  private usr: string;
  private password: string;
  private userType: string;
  private firstname: string;
  private lastname: string;

  constructor(private adminService: AdminService)
  {
    this.usr = "";
    this.password = "";
    this.userType = "";
    this.firstname = "";
    this.lastname = "";
  }

  register()
  {
    this.adminService.registerUser(this.usr, this.password, this.userType, this.firstname, this.lastname).subscribe(
      data => console.log(data),
    );
  }
}
