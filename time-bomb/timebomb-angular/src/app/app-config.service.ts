import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {
  public url: string = "http://localhost:8080/api";
  public httpOptions: Object = null;
  public httpOptionsSse: Object = null;

  constructor() { }

  public setCredentials(user: User) {
    let headers: HttpHeaders = new HttpHeaders();
    let basicAuth = 'Basic ' + btoa(`${ user.username }:${ user.password }`);

    headers = headers.append('Authorization', basicAuth);
    this.httpOptions = { headers: headers };
    
    this.httpOptionsSse = {
      headers: {
        'Authorization': basicAuth
      }
    };
  }
}
