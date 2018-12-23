import {Post} from "../posts/post.model";
import {User} from "../user/user.model";

export class Comment {
  postId: number;
  context: string;

  commentDate: Date
  commentId: number;
  commenter: User;

  constructor(obj: Post) {

    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }
}

export declare type Comments = Comment[];
