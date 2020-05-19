import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { SopraBoldElementComponent } from './sopra-bold-element/sopra-bold-element.component';
import { SopraBoldComponent } from './sopra-bold/sopra-bold.component';
import { HomeComponent } from './home/home.component';
import { VisiteComponent } from './visite/visite.component';
import { VisiteCrudRowComponent } from './visite-crud-row/visite-crud-row.component';
import { PrixCategoryPipe } from './prix-category.pipe';
import { PatientComponent } from './patient/patient.component';



const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'visite', component: VisiteComponent },
  { path: 'patient', component: PatientComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', component: HomeComponent }
];


@NgModule({
  declarations: [
    AppComponent,
    SopraBoldComponent,
    SopraBoldElementComponent,
    HomeComponent,
    VisiteComponent,
    VisiteCrudRowComponent,
    PrixCategoryPipe,
    PatientComponent
  ],
  imports: [
    BrowserModule, FormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }