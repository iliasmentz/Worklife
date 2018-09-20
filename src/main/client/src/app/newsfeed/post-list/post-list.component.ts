import {Component, Input, OnInit} from '@angular/core';
import {Posts} from "../../shared/posts/post.model";
import {Comments} from "../../shared/comments/comment.model";
import {CommentService} from "../../shared/comments/comment.service";
import {BsModalService, ModalOptions} from "ngx-bootstrap";
import {CommentComponent} from "./comment/comment.component";
import {LikeService} from "../../shared/likes/like.service";
import {Like, Likes} from "../../shared/likes/like.model";
import {LikeComponent} from "./like/like.component";

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
  @Input() userId: number;
  @Input() posts: Posts = [];
  comments: Comments;
  likes: Likes = [];

  constructor(private commentService: CommentService,
              private _modal: BsModalService,
              private likeService: LikeService,
  ) {
  }

  ngOnInit() {
    this.likeService.getUserLikes(this.userId)
      .then((likes: Likes) => {
        this.likes = likes
      });
    this.likeService.like.subscribe((like: Like) => {
      this.likes.push(like);
      var post = this.posts.find(x => x.postId === like.postId);
      post.numberOfLikes++;
    })
  }

  showLikes(postId: number) {
    console.log(postId);
    this.likeService.getPostLikes(postId)
      .then((postLikes: Likes) => {
        const initialState = {
          likes: postLikes,
        };

        this._modal.show(LikeComponent, {...options, initialState});
      });
  }

  isLiked(postId: number): boolean {
    return this.likes.find(x => x.postId === postId) != null;
  }

  showComments(postId: number) {
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

  likePost(postId: number) {
    this.likeService.addLike(postId)
      .then((like: Like) => {
        this.likeService.like.next(like)
      });
  }

  unlikePost(postId: number) {
    let like = this.likes.find(x => x.postId === postId);
    this.likeService.deleteLike(like.likeId);
    let post = this.posts.find(x => x.postId === postId);
    post.numberOfLikes--;
    this.likes = this.likes.filter(function (obj) {
      return obj.postId !== postId;
    });
  }
}
