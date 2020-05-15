import { Component } from '@angular/core';
import { Visite } from './visite';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'hopital';
  couleur = "pink";
  visite = new Visite();
  
  visites: Array<Visite> = [
    new Visite(1, 50),
    new Visite(2, 42),
    new Visite(3, 400),
    new Visite(4, 648),
    new Visite(5, 499.66)
  ];


  public changeTitle() {
    this.title = "QUELQUE CHOSE D'AUTRE ICI";
  }
}