import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PostDto} from "../../shared/posts/post-dto.model";
import {PostService} from "../../shared/posts/post.service";

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.css']
})
export class PostFormComponent implements OnInit {
  postForm: FormGroup;

  constructor(private _fb: FormBuilder, private postService: PostService) {
  }

  ngOnInit() {
    this.postForm = this._fb.group({
      context: [null, Validators.required],
      visible: [null, Validators.required]
    });
  }

  onSubmit(postForm: FormGroup) {
    if (postForm.valid) {
      let postRequest = new PostDto();
      postRequest.load(this.postForm);
      this.postService.addPost(postRequest)
        .then(() => {
        });
    }
  }
}
