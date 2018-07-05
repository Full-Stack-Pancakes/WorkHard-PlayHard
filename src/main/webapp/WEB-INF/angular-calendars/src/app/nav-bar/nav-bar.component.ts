import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Globals } from '../global';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  constructor(private global:Globals) { }
  private loginSubscription: Subscription;
  isLogin: Boolean=false;
  ngOnInit() {   
    this.isLogin=this.global.isSignIn;
    this.loginSubscription=this.global.update.subscribe(()=>{
    this.isLogin=this.global.getSignIn();
    });
  }
  ngOnDestroy(){
    this.loginSubscription.unsubscribe();
  }
}
