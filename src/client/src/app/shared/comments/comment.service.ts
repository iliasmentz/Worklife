import {Injectable} from "@angular/core";
import {RepoService} from "../repo/repo.service";
import {Comment, Comments} from "./comment.model";
import {map} from "rxjs/operators";
import {CommentDto} from "./comment-dto.model";
import {Subject} from "rxjs";

@Injectable()
export class CommentService {
  comment = new Subject();

  constructor(private repoService: RepoService) {
  }

  getComments(postId: number): Promise<Comments> {
    return this.repoService.get('comments/post/' + postId)
      .pipe(map((comments: any[]) => {
        return comments.map(comment => this.deserializeComment(comment))
      }))
      .toPromise() as Promise<Comments>;
  }

  updateComment(commentId: number, commentRequest: CommentDto) {
    return this.repoService.put("comments/post/" + commentId, commentRequest)
      .pipe(map(comment => this.deserializeComment(comment)))
      .toPromise() as Promise<Comment>;
  }

  addComment(commentRequest: CommentDto) {
    return this.repoService.post("comments/post/", commentRequest)
      .pipe(map(comment => this.deserializeComment(comment)))
      .toPromise() as Promise<Comment>;
  }

  deleteComment(commentId: number) {
    this.repoService.delete("comments/post/" + commentId)
      .subscribe(() => {
      }, error => console.log(error));
  }

  private deserializeComment(resp): Comment {
    return new Comment(resp);
  }
}
