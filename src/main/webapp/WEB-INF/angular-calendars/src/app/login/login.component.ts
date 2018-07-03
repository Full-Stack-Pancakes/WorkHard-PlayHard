import { Component, OnInit } from '@angular/core';
import { NgForm} from '@angular/forms'

declare const gapi;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  CLIENT_ID: string = '71981442606-teuh4dts215oti8e39nh91q5u43uj0bq.apps.googleusercontent.com'
  API_KEY: string = 'AIzaSyBQBa3p9q0qkknOuXNmA2saBvMDcl10mJI';
  DISCOVERY_DOCS: string[]= ["https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest"];
  SCOPES: string = "https://www.googleapis.com/auth/calendar";
  constructor() { }

  ngOnInit() {
  }

  onSubmit(form: NgForm)
  {
    console.log(form);
    console.log(form.value.username);
    console.log(form.value.password)

  }

  signInGoogle() 
  {
    gapi.load('client:auth2', ()=>
    {
      console.log("HINNIN");
      gapi.client.init
      ({
      apiKey: this.API_KEY,
      clientId: this.CLIENT_ID,
      discoveryDocs: this.DISCOVERY_DOCS,
      scope: this.SCOPES  
      });
      gapi.auth2.getAuthInstance().signIn();
      
    })
  }
  signOutGoogle(){
    gapi.auth2.getAuthInstance().signOut();
    console.log("LOGOUT>>");
  }
}
