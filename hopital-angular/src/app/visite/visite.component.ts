import { Component, OnInit } from '@angular/core';
import { Visite } from '../visite';

@Component({
  selector: 'app-visite',
  templateUrl: './visite.component.html',
  styleUrls: ['./visite.component.css']
})
export class VisiteComponent implements OnInit {
  visite = new Visite();
  filterPrix: number;
  
  visites: Array<Visite> = [
    new Visite(1, 50),
    new Visite(2, 42),
    new Visite(3, 400),
    new Visite(4, 648),
    new Visite(5, 499.66)
  ];

  constructor() { }

  ngOnInit() {
  }


  public ajouterVisite() {
    this.visites.push(this.visite);
    this.visite = new Visite();
  }

  public visitesFiltered() {
    if (this.filterPrix || this.filterPrix === 0) {
     return this.visites.filter(v =>
        v.prix == this.filterPrix || v.id == this.filterPrix
      );
    }

    return this.visites;
  }
}
