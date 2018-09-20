import {Component, Input, OnInit} from '@angular/core';
import {Post} from "../../shared/posts/post.model";
import {PostService} from "../../shared/posts/post.service";
import {BsModalService, ModalOptions} from "ngx-bootstrap";
import {PostModalComponent} from "./post-modal/post-modal.component";
import {ActivatedRoute, Params} from "@angular/router";

const options: ModalOptions = {
  class: 'modal-m',
  backdrop: 'static',
};

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['../css/bootstrap.css', '../css/font-awesome.css', '../css/theme.css']
})
export class PostsComponent implements OnInit {

  @Input() posts: Post[];
  @Input() userId: number;
  today: Date;

  constructor(private route: ActivatedRoute,
              private _modal: BsModalService,
              private postService: PostService) {
  }

  ngOnInit() {
    this.today = new Date();
    this.route.params.subscribe((params: Params) => {
      if (this.myProfileUsername(params['username'])) {
        this.postService.post.subscribe((newPost: Post) => {
          let updateItem = this.posts.find(x => x.postId === newPost.postId);
          if (updateItem != null) {
            let index = this.posts.indexOf(updateItem);
            this.posts[index] = newPost;
          } else {
            this.posts.push(newPost);
          }
        })

      }
    });
  }

  myProfileUsername(username: string): boolean {
    let user = JSON.parse(localStorage.getItem('currentUser'));
    return user.username === username;
  }


  myProfile(): boolean {
    let user = JSON.parse(localStorage.getItem('currentUser'));
    return user.userId === this.userId;
  }

  openEditModal(postId: number) {
    const initialState = {
      post: this.posts.find(x => x.postId === postId),
      mode: 'Update'
    };

    this._modal.show(PostModalComponent, {...options, initialState});
  }

  deletePost(postId: number) {
    this.postService.deletePost(postId);
    this.posts = this.posts.filter(function (obj) {
      return obj.postId !== postId;
    });
  }

  showLikes() {
    console.log("likes");
  }
}
