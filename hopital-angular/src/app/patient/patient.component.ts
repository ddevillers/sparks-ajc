import { Component, OnInit } from '@angular/core';
import { PatientService } from '../patient.service';
import { Patient } from '../patient';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {
  private formPatient: Patient = new Patient();
  private isEditing = false;

  constructor(private srvPatient: PatientService) { }

  ngOnInit() {
    this.srvPatient.reload();
  }

  public ajouterPatient() {
    this.srvPatient.add(this.formPatient);
    this.formPatient = new Patient();
  }

  public modifierPatient() {
    this.isEditing = false;
    this.srvPatient.update(this.formPatient);
    this.formPatient = new Patient();
  }

  public annulerModification() {
    this.isEditing = false;
    this.formPatient = new Patient();
  }

  public editerPatient(patient) {
    this.formPatient = JSON.parse(JSON.stringify(patient));
    this.isEditing = true;
  }

  public supprimerPatient(patient) {
    this.srvPatient.delete(patient);
  }
}