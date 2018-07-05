import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CalendarModule } from 'angular-calendar';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { CalendarComponent } from './calendar/calendar.component';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { AboutComponent } from './about/about.component';
import { TasksComponent } from './tasks/tasks.component';
import { MaterialModule } from './material/material.module';
import { LoginComponent } from './login/login.component';
import { RoutingModule } from './routing/routing.module';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import {GooglePlaceModule} from 'ngx-google-places-autocomplete'

@NgModule({
  declarations: [
    LoginComponent,
    AppComponent,
    HomeComponent,
    ProfileComponent,
    AboutComponent,
    TasksComponent,
    NavBarComponent,
    CalendarComponent

  ],
  imports: [
    BrowserModule, 
    MaterialModule,
    RoutingModule,
    GooglePlaceModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    NgbModalModule.forRoot(),
    CalendarModule.forRoot()
  
  ],
  providers: [],
  bootstrap: [AppComponent],
  exports: [CalendarComponent]
})
export class AppModule { }