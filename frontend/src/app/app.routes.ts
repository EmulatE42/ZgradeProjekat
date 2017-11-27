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
    }
  ];

export const routing : ModuleWithProviders = RouterModule.forRoot(appRoutes);
