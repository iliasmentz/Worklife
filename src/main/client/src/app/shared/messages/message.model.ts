export class Message {
  message: string;
  sendBy: number;
  sendDate: Date;

  constructor(obj: Message) {
    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }
}

export declare type Messages = Message[];
