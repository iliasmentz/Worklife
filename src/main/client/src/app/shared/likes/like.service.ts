import {Injectable} from "@angular/core";
import {RepoService} from "../repo/repo.service";
import {Like, Likes} from "../likes/like.model";
import {map} from "rxjs/operators";
import {Subject} from "rxjs";

@Injectable()
export class LikeService {
  like = new Subject();

  constructor(private repoService: RepoService) {
  }

  getUserLikes(userId: number): Promise<Likes> {
    return this.repoService.get('likes/user/' + userId)
      .pipe(map((likes: any[]) => {
        return likes.map(like => this.deserializeLike(like))
      }))
      .toPromise() as Promise<Likes>;
  }

  getPostLikes(userId: number): Promise<Likes> {
    return this.repoService.get('likes/' + userId)
      .pipe(map((likes: any[]) => {
        return likes.map(like => this.deserializeLike(like))
      }))
      .toPromise() as Promise<Likes>;
  }

  addLike(postId: number) {
    return this.repoService.post("likes/" + postId)
      .pipe(map(like => this.deserializeLike(like)))
      .toPromise() as Promise<Like>;
  }

  deleteLike(likeId: number) {
    this.repoService.delete("likes/" + likeId)
      .subscribe(() => {
      }, error => console.log(error));
  }

  private deserializeLike(resp): Like {
    return new Like(resp);
  }
}
