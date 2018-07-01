import { Component, OnInit } from '@angular/core';



declare const gapi: any;


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

 CLIENT_ID: string = '71981442606-teuh4dts215oti8e39nh91q5u43uj0bq.apps.googleusercontent.com'
 API_KEY: string = 'AIzaSyBQBa3p9q0qkknOuXNmA2saBvMDcl10mJI';
 DISCOVERY_DOCS: string[]= ["https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest"];
 SCOPES: string = "https://www.googleapis.com/auth/calendar";
  

  constructor() { }

  ngOnInit() {
    function hello(){console.log("HELO");}


  }

  handleClientLoad() {
    gapi.load('client:auth2', ()=>{});
  }


  

  





}
