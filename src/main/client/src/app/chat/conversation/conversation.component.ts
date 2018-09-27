import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MessageService} from "../../shared/messages/message.service";
import {Conversation} from "../../shared/messages/conversation.model";
import {User} from "../../shared/user/user.model";
import {interval} from "rxjs";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-conversation',
  templateUrl: './conversation.component.html',
  styleUrls: ['./conversation.component.css']
})
export class ConversationComponent implements OnInit, OnDestroy {
  conversation: Conversation = new Conversation();
  user: User;
  userId: number;
  public timeObservable: any;
  intervalTimer = interval(5000);
  conversationForm: FormGroup;

  constructor(private _route: ActivatedRoute,
              private _fb: FormBuilder,
              private _messageService: MessageService) {
  }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));

    this.conversationForm = this._initForm();
    this._route.data.subscribe(resolvedData => {
      this.conversation = resolvedData['conversation'];
      this.userId = this.conversation.user.userId;
    });
    this.timeObservable = this.intervalTimer
      .subscribe(() => {
        this.getMessages(this.userId);
      });

  }

  private _initForm = () => {
    return this._fb.group({
      message: [null]
    });
  }

  private getMessages(userId: number) {
    this._messageService.getConversation(userId)
      .then((messages: Conversation) => {
        this.conversation = messages;
      })
  }

  onSubmit(form: FormGroup) {
    let message = form.get('message').value;

    this._messageService.sendMessage(this.userId, message)
      .then(() => {
        this.getMessages(this.userId);
      })
  }

  ngOnDestroy(): void {
    this.timeObservable.unsubscribe();
  }
}
