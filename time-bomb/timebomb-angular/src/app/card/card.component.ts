import { Component, OnInit, Input } from '@angular/core';
import { UserService } from '../user.service';
import { Card } from '../card';
import { MatchService } from '../match.service';
import { Match } from '../match';

@Component({
  selector: 'card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  @Input() private match: Match = null;
  @Input() private card: Card = null;
  @Input() private reveale: boolean = false;

  constructor(private srvMatch: MatchService, private srvUser: UserService) { }

  ngOnInit() {
  }

  public getCardImage() {
    let img = (this.reveale && this.card.type) || (this.match.state == 'TERMINATED' && this.card.type) || "back";
    return `/assets/img/${ img.toLowerCase() }.jpg`;
  }

  public play(card: Card) {
    this.srvMatch.play(this.match, card);
  }
}
