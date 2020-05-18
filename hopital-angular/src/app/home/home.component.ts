import { Component, OnInit } from '@angular/core';
import { Visite } from '../visite';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  title = 'hopital';
  couleur = "pink";
  visite = new Visite();

  constructor() { }

  ngOnInit() {
  }

  public changeTitle() {
    this.title = "QUELQUE CHOSE D'AUTRE ICI";
  }
}
