import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  issignup: boolean = false;
  title = 'Auto Scheduling Calendar';

  signup(){
    this.issignup = !this.issignup;
  }
}
