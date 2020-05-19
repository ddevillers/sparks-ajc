import { Component, OnInit, Input, HostListener } from '@angular/core';
import { Visite } from '../visite';
import { VisiteService } from '../visite.service';
import { PatientService } from '../patient.service';

@Component({
  selector: '[visite-crud-row]',
  templateUrl: './visite-crud-row.component.html',
  styleUrls: ['./visite-crud-row.component.css']
})
export class VisiteCrudRowComponent implements OnInit {
  @Input('visite-crud-row') private visite: Visite;
  private oldVisite: Visite;

  private isNew: boolean = false;
  private isEditing: boolean = false;
  private isDeleting: boolean = false;

  constructor(private srvVisite: VisiteService, private srvPatient: PatientService) { }


  //Méthode exécutée après l'initialisation du composant
  public ngOnInit() {
    if (!this.visite) {
      this.visite = new Visite();
      this.isNew = true;
    }
  }


  //Action au double-clique
  @HostListener('dblclick')
  public onDblClick() {
    if (!this.isEditing) {
      //On fait une copie de la visite visite (pour pouvoir revenir en arrière)
      this.oldVisite = JSON.parse(JSON.stringify(this.visite));

      //La vue va s'adapter en mode Edition
      this.isEditing = true;
    }
  }

  //Action à la saisie d'une touche au clavier
  @HostListener('keydown', ['$event'])
  public onKeyDown(e) {
    if (e.keyCode == 13) { //Si c'est entrée, on valide
      this.edit();
    }
  }


  /*
    * Edition d'une visite
    */
  public edit() {
    this.isEditing = false;

    if (this.isNew) {
      this.srvVisite.add(this.visite);
      this.visite = new Visite();
    }

    else {
      this.srvVisite.update(this.visite);
    }
  }


  /*
    * Annuler l'édition ou l'ajout
    */
  public cancel() {
    if (this.oldVisite) {
      //On remet les données de la visite avant les modifications
      let index = this.srvVisite.visites.indexOf(this.visite);
      this.visite = JSON.parse(JSON.stringify(this.oldVisite));
      this.srvVisite.visites.splice(index, 1, this.visite);
    }

    this.isEditing = false;
    this.isDeleting = false;
  }



  /*
    * Demander la suppression (active dans la vue la validation de la suppression)
    */
  public askDelete() {
    this.isDeleting = true;
  }


  /*
    * Supprimer une visite
    */
  public delete() {
    this.srvVisite.delete(this.visite);
  }
}