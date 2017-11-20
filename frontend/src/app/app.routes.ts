import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AboutComponent } from './components/about/about.component';
import { HomeComponent} from './components/home/home.component';
import {AddUserComponent} from "./components/addUser/addUser.component";
import {BuildingComponent} from "./components/building/building.component";
import {AdminPageComponent} from "./components/adminPage/adminPage.component";

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
    }
  ];

export const routing : ModuleWithProviders = RouterModule.forRoot(appRoutes);
