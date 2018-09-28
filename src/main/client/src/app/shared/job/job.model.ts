import {User} from "../user/user.model";

export class Job {
  id: number;
  title: string;
  author: User;
  company: string;
  dateCreated: Date;
  description: string;
  skills: string[];

  constructor(obj: Job) {

    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }
}

export declare type Jobs = Job[];
