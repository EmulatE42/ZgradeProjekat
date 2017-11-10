import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';



import { NavbarComponent } from './components/navbar/navbar.component';
import { AppComponent }  from './app.component';
import {HomeComponent} from "./components/home/home.component";
import {AboutComponent} from "./components/about/about.component";

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
    AboutComponent
  ],
  providers: [],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
