import { Injectable } from '@angular/core';
import { AppConfigService } from './app-config.service';
import { User } from './user';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService implements CanActivate {
  private apiUrl: string = "";
  public current: User = new User();

  constructor(
            private appConfig: AppConfigService,
            private http: HttpClient,
            private router: Router) {
    this.apiUrl = `${ this.appConfig.url }/users`;
  }

  public canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.current && this.current.id) {
      return true;
    }

    this.router.navigate([ '/home' ]);
  }

  public login(user: User) {
    this.http.post(`${ this.apiUrl }/login`, user)
      .subscribe(respUser => {
        this.current = respUser;
        this.current.username = user.username;
        this.current.password = user.password;

        this.appConfig.setCredentials(this.current);
        this.router.navigate([ '/matches' ]);
      });
  }

  public subscribe(user: User) {
    return this.http.post(`${ this.apiUrl }/subscribe`, user).toPromise();
  }
}
