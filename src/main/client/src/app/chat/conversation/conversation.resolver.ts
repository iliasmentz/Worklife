import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {Conversation} from "../../shared/messages/conversation.model";
import {MessageService} from "../../shared/messages/message.service";

@Injectable()
export class ConversationResolver implements Resolve<Promise<Conversation>> {

  constructor(private conversationService: MessageService) {

  }

  resolve(route: ActivatedRouteSnapshot): Promise<Conversation> {
    const userId = +route.params['userId'];
    return this.conversationService.getConversation(userId);
  }
}
