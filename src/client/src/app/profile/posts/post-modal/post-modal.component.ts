import {Component, OnInit} from '@angular/core';
import {BsModalRef} from 'ngx-bootstrap';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Post} from "../../../shared/posts/post.model";
import {PostService} from "../../../shared/posts/post.service";
import {PostDto} from "../../../shared/posts/post-dto.model";

@Component({
  selector: 'app-post-modal',
  templateUrl: './post-modal.component.html'
})
export class PostModalComponent implements OnInit {

  mode: string;
  post: Post;
  postForm: FormGroup;

  constructor(public _modal: BsModalRef, private _fb: FormBuilder,
              private postService: PostService) {
  }

  close() {
    if (this._modal) {
      this._modal.hide();
      this._modal = null;
    }
  }

  ngOnInit() {
    if (this.mode === 'Update') {
      this.postForm = this._editForm();
    } else {
      this.postForm = this._addForm();
    }
  }


  private _editForm = () => {
    return this._fb.group({
      context: [this.post.context, Validators.required],
      visible: [this.post.visible, Validators.required],
    });
  }

  private _addForm = () => {
    return this._fb.group({
      context: [this.post.context, Validators.required],
      visible: [this.post.visible, Validators.required],
    });
  }

  onSubmit(form: FormGroup) {
    if(!form.valid) {
      return;
    }
    let postRequest = new PostDto();
    postRequest.load(form);

    if (this.mode === 'Update') {
      this.postService.updatePost(this.post.postId, postRequest)
        .then(post => {
          this.post = post;
          this.postService.post.next(post);
        });
    } else {
      this.postService.addPost(postRequest)
        .then(post => {
          this.post = post;
          this.postService.post.next(post);
        });
    }
    this._modal.hide();
    this._modal = null;
  }

}
