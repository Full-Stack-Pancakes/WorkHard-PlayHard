import { NgModule } from '@angular/core';
import { Routes, RouterModule} from '@angular/router';

import { ProfileComponent} from '../profile/profile.component';
import { AboutComponent} from '../about/about.component';
import { HomeComponent} from '../home/home.component';
import { LoginComponent} from '../login/login.component';
import { TasksComponent} from '../tasks/tasks.component';
import { CalendarComponent } from '../calendar/calendar.component';


const routes: Routes =[
  { path:"", component: HomeComponent },
  { path:"home", component: HomeComponent },
  { path:"login", component: LoginComponent },
  { path:"profile", component: ProfileComponent },
  { path:"about", component: AboutComponent },
  { path:"tasks", component: TasksComponent },
  { path:"calendar", component: CalendarComponent}
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)

  ],
  exports: [RouterModule]

})
export class RoutingModule { }
