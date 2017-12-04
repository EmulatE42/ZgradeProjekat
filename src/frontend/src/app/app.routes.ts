import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AboutComponent } from './components/about/about.component';
import { HomeComponent} from './components/home/home.component';
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

const appRoutes : Routes =
  [
    {
      path : '',
      component : HomeComponent
    },
    {
      path : 'about',
      component : AboutComponent
    },
    {
      path : 'addUser',
      component : AddUserComponent
    },
    {
      path : "makeBuilding",
      component : BuildingComponent
    },
    {
      path : "adminPage",
      component : AdminPageComponent
    },
    {
      path : "displayBuilding/:p1",
      component : DisplayBuildingComponent
    },
    {
      path : "addLocation/:p1",
      component : AddLocationComponent
    },
    {
      path : "location/:p1",
      component : LocationComponent
    },
    {
      path : "addResposiblePerson/:p1",
      component : AddResponsiblePersonComponent
    },
    {
      path : "showAllResponsiblePersonsComponent/:p1",
      component : ShowAllResponsiblePersonsComponent
    },
    {
      path : "setBuildingManagerComponent/:p1",
      component : SetBuildingManagerComponent
    },
    {
      path : "tenantPageComponent",
      component : TenantPageComponent
    },
    {
      path : "showAllBugs/:p1",
      component : ShowAllBugsComponent
    },
    {
      path : "addNewBug/:p1",
      component : AddNewBugComponent
    },
    {
      path : "bug/:p1",
      component : BugComponent
    },
    {
      path : "responsiblePersonBugs",
      component : InstitutionBugsComponent
    },
    {
      path : "createBill/:p1",
      component : BillComponent
    },
    {
      path : "setResponsibleFirm/:p1",
      component : SetResponsibleFirmComponent
    },
    {
      path : "firmPage",
      component : FirmPageComponent
    },
    {
      path: "showBill/:p1/:p2",
      component: ShowBillComponent
    },
    {
      path : "parlaments",
      component : ParlamentViewComponent
    },
    {
      path : "parlament/:p1/sessions",
      component : SessionViewComponent
    }

  ];

export const routing : ModuleWithProviders = RouterModule.forRoot(appRoutes);

