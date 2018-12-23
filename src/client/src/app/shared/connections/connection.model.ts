import {User} from "../user/user.model";

export class Connection {
  connectionId: number;
  connectedUser:	User;
  connectionRequestId: number;
  createDate:	Date;

  constructor(obj: any) {
    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }

}

export declare type Connections = Connection[];

