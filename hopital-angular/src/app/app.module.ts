import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { SopraBoldElementComponent } from './sopra-bold-element/sopra-bold-element.component';
import { SopraBoldComponent } from './sopra-bold/sopra-bold.component';
import { HomeComponent } from './home/home.component';
import { VisiteComponent } from './visite/visite.component';
import { VisiteCrudRowComponent } from './visite-crud-row/visite-crud-row.component';
import { PrixCategoryPipe } from './prix-category.pipe';



const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'visite', component: VisiteComponent },
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
    PrixCategoryPipe
  ],
  imports: [
    BrowserModule, FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }