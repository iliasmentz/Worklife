import {Component, Input, OnInit} from '@angular/core';
import {Posts} from "../../shared/posts/post.model";
import {Comments} from "../../shared/comments/comment.model";
import {CommentService} from "../../shared/comments/comment.service";
import {BsModalService, ModalOptions} from "ngx-bootstrap";
import {CommentComponent} from "./comment/comment.component";

const options: ModalOptions = {
  class: 'modal-md',
  backdrop: 'static',
};


@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {
  @Input() posts: Posts;
  comments: Comments;

  constructor(private commentService: CommentService,
              private _modal: BsModalService,
              ) { }

  ngOnInit() {
  }

  showLikes(postId: number) {
    console.log(postId);
  }

  showComments(postId: number) {
    console.log(postId);
    this.commentService.getComments(postId).then((postComments: Comments) => {
      this.comments = postComments;
    })
  }

  addComment(postId: number) {
    const initialState = {
      postId: postId,
      mode: 'Add'
    };

    this._modal.show(CommentComponent, {...options, initialState});
  }
}
