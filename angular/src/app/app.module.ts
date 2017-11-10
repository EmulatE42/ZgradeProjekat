import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NavbarComponent } from './components/navbar/navbar.component';

import { AppComponent }  from './app.component';
import {HomeComponent} from "./components/home/home.component";
import {AboutComponent} from "./components/about/about.component";

import { routing } from "./app.routes";

@NgModule({
  imports:      [
    BrowserModule,
    routing,
  ],
  declarations: [
    AppComponent,
    NavbarComponent,

    HomeComponent,
    AboutComponent
  ],
  providers: [],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
