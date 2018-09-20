import {Injectable} from "@angular/core";
import {RepoService} from "../repo/repo.service";
import {map} from "rxjs/operators";
import {PostDto} from "../posts/post-dto.model";
import {Post, Posts} from "./post.model";
import {Subject} from "rxjs";


@Injectable()
export class PostService {
  post = new Subject();

  constructor(private repoService: RepoService,) {
  }

  getUserPost(userId: number): Promise<Posts> {
    return this.repoService.get('posts/users/' + userId)
      .pipe(map((posts: any[]) => {
        return posts.map(post => this.deserializePost(post))
      }))
      .toPromise() as Promise<Posts>;
  }

  updatePost(postId: number, postRequest: PostDto) {
    return this.repoService.put("posts/" + postId, postRequest)
      .pipe(map(post => this.deserializePost(post)))
      .toPromise() as Promise<Post>;
  }

  addPost(postRequest: PostDto | FormData) {
    return this.repoService.post("posts/", postRequest)
      .pipe(map(post => this.deserializePost(post)))
      .toPromise() as Promise<Post>;
  }

  deletePost(postId: number) {
    this.repoService.delete("posts/" + postId)
      .subscribe(() => {
      }, error => console.log(error));
  }

  private deserializePost(resp): Post {
    return new Post(resp);
  }
}
