import { Component, OnInit } from '@angular/core';
import { Match } from '../match';
import { MatchService } from '../match.service';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { SseStreamService } from '../sse-stream.service';

@Component({
  selector: 'app-page-match-list',
  templateUrl: './page-match-list.component.html',
  styleUrls: ['./page-match-list.component.css']
})
export class PageMatchListComponent implements OnInit {
  private formMatch: Match = new Match(0, "", 4);

  constructor(
    private srvUser: UserService,
    private srvMatch: MatchService,
    private srvSseStream: SseStreamService,
    private router: Router) { }

  ngOnInit() {
    this.srvMatch.findAll();
    this.srvSseStream.connect(() => this.srvMatch.findAll());
  }

  public isMine(match: Match) {
    return !!match.players.find(p => p.id == this.srvUser.current.id);
  }

  public createMatch() {
    this.srvMatch.create(this.formMatch);
  }

  public async joinMatch(match: Match) {
    await this.srvMatch.join(match);
    this.router.navigate([ '/matches', match.id ]);
  }

  public deleteMatch(match: Match) {
    this.srvMatch.delete(match);
  }
}