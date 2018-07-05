import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

declare const gapi;

@Injectable({
  providedIn: 'root'
})
export class Globals {
  isSignIn: Boolean;
  myNum: String='300';
  update = new Subject();
  googleEvents=[];

  setSignIn(isTrue: Boolean){
    this.isSignIn=isTrue;
    this.myNum='200';
    this.update.next();
  }
  getSignIn() {
    return this.isSignIn;
  }
  
  getGoogleEvents(){
    gapi.client.calendar.events.list({
      'calendarId': 'primary',
      'timeMin': (new Date()).toISOString(),
      'showDeleted': false,
      'singleEvents': true,
      'maxResults': 10,
      'orderBy': 'startTime'
    }).then((response)=> {
      this.googleEvents=response.result.items;
      console.log("just populate events in googleevents");
    });
    this.update.next();
  }
}
