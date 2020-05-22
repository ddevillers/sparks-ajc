import { Injectable } from '@angular/core';
import { AppConfigService } from './app-config.service';
import { HttpClient } from '@angular/common/http';
import { Match } from './match';
import { Card } from './card';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MatchService {
  private apiUrl: string = "";
  public matches: Array<Match> = new Array<Match>();

  constructor(private appConfig: AppConfigService, private http: HttpClient) {
    this.apiUrl = `${ this.appConfig.url }/matches`;
  }

  public findAll() {
    this.http
      .get<Array<Match>>(this.apiUrl, this.appConfig.httpOptions)
      .subscribe(matches => this.matches = matches);
  }

  public findById(id: number): Observable<Match> {
    return this
      .http
      .get<Match>(`${ this.apiUrl }/${ id }`, this.appConfig.httpOptions);
  }

  public create(match: Match) {
    this.http
      .post<Match>(this.apiUrl, match, this.appConfig.httpOptions)
      .subscribe(match => this.matches.push(match));
  }

  public join(match: Match): Promise<Match> {
    return this
      .http
      .put<Match>(`${ this.apiUrl }/${ match.id }`, null, this.appConfig.httpOptions)
      .toPromise();
  }

  public start(match: Match) {
    this.http
      .put<Match>(`${ this.apiUrl }/${ match.id }/start`, null, this.appConfig.httpOptions)
      .subscribe();
  }

  public play(match: Match, card: Card) {
    this.http
      .put<Card>(`${ this.apiUrl }/${ match.id }/play`, card, this.appConfig.httpOptions)
      .subscribe();
  }

  public delete(match: Match) {
    this.http
      .delete<void>(`${ this.apiUrl }/${ match.id }`, this.appConfig.httpOptions)
      .subscribe(() => this.matches.splice(this.matches.indexOf(match), 1));
  }
}