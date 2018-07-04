import { Component, OnInit } from '@angular/core';
import { User } from '../User';
import { CalendarService } from '../calendar.service'
import { Event } from '../Event';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  user: User;
  event: Event;

  constructor(private calendarService: CalendarService) { 
    
  }

  ngOnInit() {
    this.calendarService.getUserById(62).then((data)=>this.user=data.firstname);
    this.calendarService.getEventById(2).then((data)=>this.event=data.description);
  }

}
