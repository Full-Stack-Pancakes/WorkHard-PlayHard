import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { AboutComponent } from './about/about.component';
import { TasksComponent } from './tasks/tasks.component';
import { MaterialModule } from './material/material.module';
import { LoginComponent } from './login/login.component';
import { RoutingModule } from './routing/routing.module';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { InterceptorModule } from './interceptor/interceptor.module'

@NgModule({
  declarations: [
    LoginComponent,
    AppComponent,
    HomeComponent,
    ProfileComponent,
    AboutComponent,
    TasksComponent,
    NavBarComponent,
   
   

  ],
  imports: [
    BrowserModule, 
    MaterialModule,
    RoutingModule,
    InterceptorModule,
  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }