import { Component, OnInit } from '@angular/core';

declare const gapi;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  eventtitle: String;
  eventlocation: String;
  eventdes: String;
  startdate: Date;
  enddate: Date;
  starttime: String;
  endtime: String;
  begindate: String;
  duedate: String;
  new_event: any;
  constructor() { }

  createEvent()
  {

    this.begindate = this.startdate.getFullYear()+'-'+(this.startdate.getMonth()+1).toString()+'-'+ this.startdate.getDate()+'T'+this.starttime+':00';
    this.duedate = this.enddate.getFullYear()+'-'+(this.enddate.getMonth()+1).toString()+'-'+ this.enddate.getDate()+'T'+this.endtime+':00';
    this.new_event = {
      'summary': this.eventtitle,
      'location': this.eventlocation,
      'description': this.eventdes,
      'start': {
        'dateTime': this.begindate,
        'timeZone': this.currenttz
      },
      'end': {
        'dateTime': this.duedate,
        'timeZone': this.currenttz
      }}

       gapi.client.calendar.events.insert({'calendarId': 'primary',
       'resource': this.new_event}).execute()

  }
  ngOnInit() {
  }
  currenttz: String;
  timezones: String[] = ['America/Los_Angeles', 'America/New_York', 'America/Denver', 'America/Chicago'];
  currentt: String;
  times: String[] = ['4:00', '4:15', '4:30', '4:45', '5:00', '5:15', '5:30', '5:45', '6:00', '6:15', '6:30', '6:45', '7:00', '7:15', '7:30', '7:45', '8:00', '8:15', '8:30', '8:45', '9:00', '9:15', '9:30', '9:45', '10:00', '10:15', '10:30', '10:45', '11:00', '11:15', '11:30', '11:45', '12:00', '12:15', '12:30', '12:45', '13:00', '13:15', '13:30', '13:45', '14:00', '14:15', '14:30', '14:45', '15:00', '15:15', '15:30', '15:45', '16:00', '16:15', '16:30', '16:45', '17:00', '17:15', '17:30', '17:45', '18:00', '18:15', '18:30', '18:45', '19:00', '19:15', '19:30', '19:45', '20:00', '20:15', '20:30', '20:45', '21:00', '21:15', '21:30', '21:45', '22:00', '22:15', '22:30', '22:45', '23:00', '23:15', '23:30', '23:45', '0:00', '0:15', '0:30', '0:45', '1:00', '1:15', '1:30', '1:45', '2:00', '2:15', '2:30', '2:45', '3:00', '3:15', '3:30', '3:45']



}
