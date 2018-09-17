import {Component, Input, OnInit} from '@angular/core';
import {Post} from "../../shared/posts/post.model";
import {PostService} from "../../shared/posts/post.service";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['../css/bootstrap.css', '../css/font-awesome.css', '../css/theme.css']
})
export class PostsComponent implements OnInit {

  @Input() posts: Post[];
  @Input() userId: number;
  today: Date;

  constructor(private postService: PostService) {
  }

  ngOnInit() {
    console.log("Posts " + this.posts);
    this.today = new Date();
  }

  myProfile(): boolean {
    let user = JSON.parse(localStorage.getItem('currentUser'));
    return user.userId === this.userId;
  }

  deletePost(postId: number) {
    this.postService.deleteSkill(postId);
    this.posts = this.posts.filter(function (obj) {
      return obj.postId !== postId;
    });
  }
}
