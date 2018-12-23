import {User} from "../user/user.model";

export class ConnectionRequest {
  connectionRequestId: number;
  userRequested: User;
  userTargetId: number;
  dateOfRequest: Date;
  status: number;

  constructor(obj: any) {
    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }

}

export declare type ConnectionRequests = ConnectionRequest[];

