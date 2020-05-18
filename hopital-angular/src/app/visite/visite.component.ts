import { Component, OnInit } from '@angular/core';
import { Visite } from '../visite';
import { AppConfigService } from '../app-config.service';

@Component({
  selector: 'app-visite',
  templateUrl: './visite.component.html',
  styleUrls: ['./visite.component.css']
})
export class VisiteComponent implements OnInit {
  isEditing = false;
  oldVisite = null;
  visite = new Visite();
  filterPrix: number;
  
  public visites: Array<Visite> = [
    new Visite(1, 50),
    new Visite(2, 42),
    new Visite(3, 400),
    new Visite(4, 648),
    new Visite(5, 499.66)
  ];

  constructor(private appConfig: AppConfigService) { }

  ngOnInit() {
    console.log(this.appConfig.url);
    alert(this.appConfig.url);
  }


  public ajouterVisite() {
    this.visites.push(this.visite);
    this.visite = new Visite();
  }

  public editerVisite(visite) {
    this.isEditing = true;
    this.visite = visite;
    // this.oldVisite = JSON.parse(JSON.stringify(visite));
    this.oldVisite = new Visite(visite.id, visite.prix);
  }

  public modifierVisite() {
    this.isEditing = false;
    this.visite = new Visite();
  }

  public annulerVisite() {
    let index = this.visites.indexOf(this.visite);
    this.visites.splice(index, 1, this.oldVisite);
    this.modifierVisite();
  }

  public supprimerVisite(visite) {
    let index = this.visites.indexOf(visite);
    this.visites.splice(index, 1);
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
