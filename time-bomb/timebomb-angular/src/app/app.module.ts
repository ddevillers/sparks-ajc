import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { PageHomeComponent } from './page-home/page-home.component';
import { MatchStatePipe } from './match-state.pipe';
import { PageMatchDetailedComponent } from './page-match-detailed/page-match-detailed.component';
import { PageMatchListComponent } from './page-match-list/page-match-list.component';
import { CardComponent } from './card/card.component';
import { FormControlComponent } from './form-control/form-control.component';
import { UserService } from './user.service';



const routes: Routes = [
  { path: 'home', component: PageHomeComponent },
  { path: 'matches', component: PageMatchListComponent, canActivate: [ UserService ] },
  { path: 'matches/:id', component: PageMatchDetailedComponent, canActivate: [ UserService ] },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', component: PageHomeComponent }
];


@NgModule({
  declarations: [
    AppComponent,
    PageHomeComponent,
    MatchStatePipe,
    PageMatchDetailedComponent,
    PageMatchListComponent,
    CardComponent,
    FormControlComponent
  ],
  imports: [
    BrowserModule,FormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
