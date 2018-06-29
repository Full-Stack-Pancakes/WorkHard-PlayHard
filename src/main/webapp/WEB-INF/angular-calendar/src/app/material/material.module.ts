import { NgModule } from '@angular/core';
import { FlexLayoutModule } from "@angular/flex-layout";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatDatepickerModule, 
  MatFormFieldModule, 
  MatInputModule,
  MatSidenavModule,
  MatCheckboxModule,
  MatRadioModule,
  MatSelectModule,
  MatSliderModule,
  MatCardModule,
  MatDividerModule,
  MatExpansionModule,
  MatListModule,
  MatStepperModule,
  MatButtonModule,
  MatIconModule,
  MatNativeDateModule,
  


  }from '@angular/material';

@NgModule({
  imports: [
    MatDatepickerModule, 
    MatFormFieldModule, 
    MatInputModule,
    MatSidenavModule,
    MatCheckboxModule,
    MatRadioModule,
    MatSelectModule,
    MatSliderModule,
    MatCardModule,
    MatDividerModule,
    MatExpansionModule,
    MatListModule,
    MatStepperModule,
    MatButtonModule,
    MatIconModule,
    MatNativeDateModule,
    BrowserAnimationsModule,
    FlexLayoutModule
   


   
  ],
  exports: [
    MatDatepickerModule, 
    MatFormFieldModule, 
    MatInputModule,
    MatSidenavModule,
    MatCheckboxModule,
    MatRadioModule,
    MatSelectModule,
    MatSliderModule,
    MatCardModule,
    MatDividerModule,
    MatExpansionModule,
    MatListModule,
    MatStepperModule,
    MatButtonModule,
    MatIconModule,
    MatNativeDateModule,
    BrowserAnimationsModule,
    FlexLayoutModule
   

  ]
})
export class MaterialModule { }
