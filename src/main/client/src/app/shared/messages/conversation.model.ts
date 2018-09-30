import {Messages} from "./message.model";
import {User} from "../user/user.model";

export class Conversation {

  user: User;
  messages: Messages;

  constructor() {
  }

  load(obj) {
    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }
}
