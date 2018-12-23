import {User} from "../user/user.model";

export class Like {
  likeId: number;
  user: User;
  postId: number;

  constructor(obj: Like) {

    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }
}

export declare type Likes = Like[];
