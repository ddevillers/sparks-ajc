import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-page-home',
  templateUrl: './page-home.component.html',
  styleUrls: ['./page-home.component.css']
})
export class PageHomeComponent implements OnInit {
  private subscribeView: boolean = false;
  private formUser: User = new User(0, "", "jeremy", "123");

  constructor(private srvUser: UserService) { }

  ngOnInit() {
  }

  public toggleView() {
    this.subscribeView = !this.subscribeView;
  }

  public login() {
    this.srvUser.login(this.formUser);
  }

  public async subscribe() {
    await this.srvUser.subscribe(this.formUser);
    this.subscribeView = false;
  }
}