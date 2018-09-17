import {Injectable} from "@angular/core";
import {RepoService} from "../repo/repo.service";
import {map} from "rxjs/operators";
import {Post, Posts} from "./post.model";


@Injectable()
export class PostService {

  constructor(private repoService: RepoService,) {
  }

  getUserPost(userId: number): Promise<Posts> {
    return this.repoService.get('posts/users/' + userId)
      .pipe(map((posts: any[]) => {
        return posts.map(post => this.deserializePost(post))
      }))
      .toPromise() as Promise<Posts>;
  }

  private deserializePost(post): Post {
    return new Post(post);
  }

  deleteSkill(postId: number) {
    this.repoService.delete("posts/" + postId)
      .subscribe(() => {
      }, error => console.log(error));
  }
}
