import { Component, OnInit } from '@angular/core';
import { Match } from '../match';
import { MatchService } from '../match.service';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';
import { SseStreamService } from '../sse-stream.service';

@Component({
  selector: 'app-page-match-detailed',
  templateUrl: './page-match-detailed.component.html',
  styleUrls: ['./page-match-detailed.component.css']
})
export class PageMatchDetailedComponent implements OnInit {
  private match: Match = new Match();
  private diffusers: number = 0;
  private bomb: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private srvMatch: MatchService,
    private srvUser: UserService,
    private srvSseStream: SseStreamService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.reload(params.id);
    });

    this.srvSseStream.connect(respMatch => {
      this.init(respMatch);
    });
  }

  public reload(id) {
    this.srvMatch.findById(id)
        .subscribe(respMatch => this.init(respMatch));
  }

  public init(match) {
    this.match = match;
    this.diffusers = 0;
    this.bomb = false;

    for (let card of this.getAllCardsForPlayer(this.srvUser.current)) {
      if (card.type == 'DIFFUSE') {
        this.diffusers++;
      }

      else if (card.type == 'BOMB') {
        this.bomb = true;
      }
    }
  }

  public startMatch() {
    this.srvMatch.start(this.match);
  }

  public getCardsForPlayer(player) {
    return this.match.deck.filter(c => !c.revealed && c.ownerId == player.id);
  }

  public getAllCardsForPlayer(player) {
    return this.match.deck.filter(c => c.ownerId == player.id);
  }
}