import {User} from "../user/user.model";

export class Post {

  postId: number;
  creator: User;
  numberOfLikes: number;
  postDate: Date;
  context: string;
  visible: string;

  constructor(obj: Post) {

    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }
}

export declare type Posts = Post[];
