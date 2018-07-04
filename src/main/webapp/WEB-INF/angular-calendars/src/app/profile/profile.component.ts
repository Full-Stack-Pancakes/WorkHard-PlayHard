import { Component, OnInit } from '@angular/core';
declare const gapi: any;


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  fullname = gapi.auth2.getAuthInstance().currentUser.get().w3.ig
  pictureurl = gapi.auth2.getAuthInstance().currentUser.get().w3.Paa
  googleId =gapi.auth2.getAuthInstance().currentUser.get().getId();
  firstname= gapi.auth2.getAuthInstance().currentUser.get().w3.ofa
  lastname=gapi.auth2.getAuthInstance().currentUser.get().w3.wea
  email=gapi.auth2.getAuthInstance().currentUser.get().w3.U3
  constructor() { }
  ngOnInit() {}

}
    

