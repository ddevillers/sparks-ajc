import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfigService } from './app-config.service';
import { Patient } from './patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  private apiUrl: string = "";
  public patients: Array<Patient> = null;

  constructor(private appConfig: AppConfigService, private http: HttpClient) {
    this.apiUrl = `${ this.appConfig.url }/patient`;
  }

  public reload() {
    this.http.get<Array<Patient>>(this.apiUrl, this.appConfig.httpOptions)
        .subscribe(patients => this.patients = patients);
  }

  public add(patient) {
    this.http.post<Patient>(this.apiUrl, patient, this.appConfig.httpOptions)
        .subscribe(respPatient => this.patients.push(respPatient));
  }

  public update(patient) {
    this.http.put<Patient>(`${ this.apiUrl }/${ patient.secu }`, patient, this.appConfig.httpOptions)
        .subscribe(respPatient => {
          let index = this.patients.indexOf(this.patients.find(p => p.secu == patient.secu));
          this.patients.splice(index, 1, respPatient);
        });
  }

  public delete(patient) {
    this.http.delete<Boolean>(`${ this.apiUrl }/${ patient.secu }`, this.appConfig.httpOptions)
        .subscribe(resp => {
          if (resp) {
            let index = this.patients.indexOf(patient);
            this.patients.splice(index, 1);
          }
        });
  }
}
