import {User} from "../user/user.model";

export class Notification {
  notificationId: number;
  user: User;
  targetUserId: number;
  status: number;
  message: string;
  type: number;
  likeCommentConnectionId: number;

  constructor(obj: Notification) {

    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }
}

export declare type Notifications = Notification[];
