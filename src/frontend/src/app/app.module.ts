import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { NavbarComponent } from './components/navbar/navbar.component';
import { AppComponent }  from './app.component';
import {HomeComponent} from "./components/home/home.component";
import {AboutComponent} from "./components/about/about.component";
import {AddUserComponent} from "./components/addUser/addUser.component";
import {BuildingComponent} from "./components/building/building.component";
import {AdminPageComponent} from "./components/adminPage/adminPage.component";
import {DisplayBuildingComponent} from "./components/building/displayBuilding/displayBuilding.component";
import {AddLocationComponent} from "./components/building/addLocation/addLocation.component";
import {LocationComponent} from "./components/location/location.component";
import {AddResponsiblePersonComponent} from "./components/building/addResponsiblePerson/addResponsiblePerson.component";
import {ShowAllResponsiblePersonsComponent} from "./components/building/showAllResponsiblePersons/showAllResponsiblePersons.component";
import {SetBuildingManagerComponent} from "./components/building/setBuildingManager/setBuildingManager.component";
import {TenantPageComponent} from "./components/tenantPage/tenantPage.component";
import {ShowAllBugsComponent} from "./components/tenantPage/showAllBugs/showAllBugs.component";
import {AddNewBugComponent} from "./components/tenantPage/addNewBug/addNewBug.component";
import {BugComponent} from "./components/bug/bug.component";

import { routing } from "./app.routes";


@NgModule({
  imports:      [
    BrowserModule,
    routing,
    HttpModule,
    FormsModule
  ],
  declarations: [
    AppComponent,
    NavbarComponent,

    HomeComponent,
    AboutComponent,
    AddUserComponent,
    BuildingComponent,
    AdminPageComponent,
    DisplayBuildingComponent,
    AddLocationComponent,
    LocationComponent,
    AddResponsiblePersonComponent,
    ShowAllResponsiblePersonsComponent,
    SetBuildingManagerComponent,
    TenantPageComponent,
    ShowAllBugsComponent,
    AddNewBugComponent,
    BugComponent
  ],
  providers: [],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
