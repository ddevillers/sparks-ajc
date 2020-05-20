import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {
  public url: string = "http://localhost:8080/api";
  public httpOptions: Object = null;

  constructor() {
    let myHeaders: HttpHeaders = new HttpHeaders();
    
    myHeaders = myHeaders.append('Authorization', 'Basic ' + btoa('jordan:ajc'));
    this.httpOptions = { headers: myHeaders };
  }
}