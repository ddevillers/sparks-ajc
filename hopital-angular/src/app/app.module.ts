import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { SopraBoldComponent } from './sopra-bold/sopra-bold.component';
import { SopraBoldElementComponent } from './sopra-bold-element/sopra-bold-element.component';

@NgModule({
  declarations: [
    AppComponent,
    SopraBoldComponent,
    SopraBoldElementComponent
  ],
  imports: [
    BrowserModule, FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
