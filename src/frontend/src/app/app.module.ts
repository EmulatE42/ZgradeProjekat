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
    LocationComponent
  ],
  providers: [],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
