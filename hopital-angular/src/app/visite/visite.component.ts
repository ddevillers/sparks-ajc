import { Component, OnInit } from '@angular/core';
import { Visite } from '../visite';
import { VisiteService } from '../visite.service';
import { PatientService } from '../patient.service';

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

  constructor(private srvVisite: VisiteService, private srvPatient: PatientService) { }

  ngOnInit() {
    this.srvVisite.reload();
    this.srvPatient.reload();
  }

  public visitesFiltered() {
    if (this.filterPrix || this.filterPrix === 0) {
     return this.srvVisite.visites.filter(v =>
        v.prix == this.filterPrix || v.id == this.filterPrix
      );
    }

    return this.srvVisite.visites;
  }


  public ajouterVisite() {
    // this.visite.patient = this.srvPatient.patients.find(p => p.secu == this.visite.patient.secu);
    this.srvVisite.add(this.visite);
    this.visite = new Visite();
  }

  public editerVisite(visite) {
    this.isEditing = true;
    this.visite = visite;
    this.visite.patient = this.srvPatient.patients.find(p => p.secu == this.visite.patient.secu);
    
    // this.oldVisite = JSON.parse(JSON.stringify(visite));
    this.oldVisite = new Visite(visite.id, visite.prix);
  }

  public modifierVisite() {
    this.srvVisite.update(this.visite);
    this.isEditing = false;
    this.visite = new Visite();
  }

  public annulerVisite() {
    this.modifierVisite();
  }

  public supprimerVisite(visite) {
    this.srvVisite.delete(visite);
  }
}
