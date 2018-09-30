import {Injectable} from "@angular/core";
import {RepoService} from "../repo/repo.service";
import {map} from "rxjs/operators";
import {ChatOverview, ChatOverviews} from "./chat-overview.model";
import {Conversation} from "./conversation.model";
import {Message} from "./message.model";

@Injectable()
export class MessageService {

  constructor(private _repoService: RepoService) {
  }

  getChatOverview(): Promise<ChatOverviews> {
    return this._repoService.get('messages/')
      .pipe(map((chatOverviews: any[]) => {
        return chatOverviews.map(chatOverview => this.deserializeChatOverview(chatOverview))
      }))
      .toPromise() as Promise<ChatOverviews>;
  }

  getConversation(userId: number): Promise<Conversation> {
    return this._repoService.get('messages/' + userId)
      .pipe(map(conv => this.deserializeConversation(conv)))
      .toPromise() as Promise<Conversation>;
  }

  sendMessage(userId: number, message: string): Promise<Message> {
    return this._repoService.post('messages/' + userId, message)
      .pipe(map(newMessage => this.deserializeMessage(newMessage)))
      .toPromise() as Promise<Message>;
  }

  private deserializeChatOverview(resp): ChatOverview {
    return new ChatOverview(resp);
  }

  private deserializeMessage(resp): Message {
    return new Message(resp);
  }

  private deserializeConversation(resp): Conversation {
    let conv = new Conversation();
    conv.load(resp);
    return conv
  }
}
