import {Injectable} from "@angular/core";
import {Posts} from "../../shared/posts/post.model";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {User} from "../../shared/user/user.model";
import {PostService} from "../../shared/posts/post.service";

@Injectable()
export class PostsResolver implements Resolve<Promise<Posts>> {
  constructor(private postService: PostService) {
  }

  resolve(route: ActivatedRouteSnapshot): Promise<Posts> {
    const userId = (route.parent.data.user as User).userId;
    return this.postService.getUserPost(userId);
  }

}
