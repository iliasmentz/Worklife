import {Component, OnInit} from '@angular/core';
import {User} from "../shared/user/user.model";
import {Posts} from "../shared/posts/post.model";
import {PostService} from "../shared/posts/post.service";

@Component({
  selector: 'app-newsfeed',
  templateUrl: './newsfeed.component.html',
  styleUrls: ['./newsfeed.component.css']
})
export class NewsfeedComponent implements OnInit {

  defaultPhoto = '/assets/img/user.svg';
  currentUser: User;
  posts: Posts;

  constructor(private postService: PostService) {
  }

  ngOnInit() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.postService.getUserPost(this.currentUser.userId)
      .then((newsfeed: Posts) => {
        this.posts = newsfeed;
      })
  }

}
