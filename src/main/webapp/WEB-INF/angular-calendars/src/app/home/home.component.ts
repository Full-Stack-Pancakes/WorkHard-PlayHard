import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  eventtitle: string;
  eventlocation: string;
  eventdes: string;
  startdate: Date;
  enddate: Date;
  starttime: string;
  endtime: string;

  constructor() { }

  ngOnInit() {
  }
  currenttz: string;
  timezones: string[] = ['America/Los_Angeles', 'America/New_York', 'America/Denver', 'America/Chicago'];




}
