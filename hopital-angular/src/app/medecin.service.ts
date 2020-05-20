import { Injectable } from '@angular/core';
import { AppConfigService } from './app-config.service';
import { HttpClient } from '@angular/common/http';
import { Medecin } from './medecin';

@Injectable({
  providedIn: 'root'
})
export class MedecinService {
  private apiUrl: string = "";
  public medecins: Array<Medecin> = null;

  constructor(private appConfig: AppConfigService, private http: HttpClient) {
    this.apiUrl = `${ this.appConfig.url }/medecin`;
  }

  public reload() {
    this.http.get<Array<Medecin>>(this.apiUrl, this.appConfig.httpOptions)
        .subscribe(medecins => this.medecins = medecins);
  }
}