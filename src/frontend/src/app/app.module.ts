import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './auth/token.interceptor';
import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';

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
import {InstitutionBugsComponent} from "./components/bug/institutionBugs/institutionBugs.component";
import {BillComponent} from "./components/bug/bill/bill.component";
import {SetResponsibleFirmComponent} from "./components/bug/setResponsibleFirm/setResponsibleFirm.component";
import {FirmPageComponent} from "./components/firmPage/firmPage.component";
import {ShowBillComponent} from "./components/tenantPage/showBill/showBill.component";
import {ParlamentViewComponent} from "./components/parlamentView/parlamentView.component";
import {SessionViewComponent} from "./components/sessionView/sessionView.component";
import {AddSessionComponent} from "./components/sessionView/addSession/addSession.component";

import { AuthenticationService } from "./services/authentication.service";

import { routing } from "./app.routes";
import {TopicViewComponent} from "./components/topicView/topicView.component";




@NgModule({
  imports:      [
    BrowserModule,
    routing,
    HttpModule,
    FormsModule,
    HttpClientModule
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
    BugComponent,
    InstitutionBugsComponent,
    BillComponent,
    SetResponsibleFirmComponent,
    FirmPageComponent,
    ShowBillComponent,
    ParlamentViewComponent,
    SessionViewComponent,
    AddSessionComponent,
    TopicViewComponent
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    AuthenticationService
  ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
