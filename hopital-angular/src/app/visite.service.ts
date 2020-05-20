import { Injectable } from '@angular/core';
import { AppConfigService } from './app-config.service';
import { Visite } from './visite';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VisiteService {
  private apiUrl: string = "";
  public visites: Array<Visite> = null;

  constructor(private appConfig: AppConfigService, private http: HttpClient) {
    this.apiUrl = `${ this.appConfig.url }/visite`;
  }

  public reload() {
    this.http.get<Array<Visite>>(this.apiUrl, this.appConfig.httpOptions)
        .subscribe(resp => this.visites = resp['_embedded'].visites );
  }

  public add(visite) {
    this.http.post<Visite>(this.apiUrl, visite, this.appConfig.httpOptions)
        .subscribe(respVisite => this.visites.push(respVisite));
  }

  public update(visite) {
    this.http.put<Visite>(`${ this.apiUrl }/${ visite.id }`, visite, this.appConfig.httpOptions)
        .subscribe();
  }

  public delete(visite) {
    this.http.delete<Boolean>(`${ this.apiUrl }/${ visite.id }`, this.appConfig.httpOptions)
        .subscribe(resp => {
            if (resp) {
              let index = this.visites.indexOf(visite);
              this.visites.splice(index, 1);
            }
          });
  }
}