export class ChatOverview {
  userId: number;
  name: string;
  lastMessage: string;
  lastMessageTime: Date;
  hasUnreadMessage: boolean;
  icon: string;

  constructor(obj: ChatOverview) {

    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }
}

export declare type ChatOverviews = ChatOverview[];
