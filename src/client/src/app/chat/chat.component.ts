import {Component, OnDestroy, OnInit} from '@angular/core';
import {ChatOverviews} from "../shared/messages/chat-overview.model";
import {MessageService} from "../shared/messages/message.service";
import {User} from "../shared/user/user.model";
import {interval} from "rxjs";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit, OnDestroy {
  chatOverviews: ChatOverviews;
  user: User;
  public timeObservable: any;
  intervalTimer = interval(5000);

  constructor(private _messageService: MessageService) {
  }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.getChatOverview();
    this.timeObservable = this.intervalTimer
      .subscribe(() => {
        this.getChatOverview();
      });
  }

  private getChatOverview() {
    this._messageService.getChatOverview()
      .then((messages: ChatOverviews) => {
        this.chatOverviews = messages;
      })
  }

  ngOnDestroy(): void {
    this.timeObservable.unsubscribe();
  }
}
