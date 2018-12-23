import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CommentDto} from "../../../shared/comments/comment-dto.model";
import {CommentService} from "../../../shared/comments/comment.service";
import {BsModalRef} from "ngx-bootstrap";

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  commentForm: FormGroup;
  postId: number;

  constructor(public _modal: BsModalRef,
              private _fb: FormBuilder,
              private commentService: CommentService) { }

  ngOnInit() {
    this.commentForm = this._fb.group({
      content: [null]
    })
  }

  onSubmit(comment: FormGroup) {
    let commentRequest = new CommentDto();
    commentRequest.load(this.postId, comment);
    this.commentService.addComment(commentRequest).then((comment) =>{
      this.close();
    })
  }

  close() {
    if (this._modal) {
      this._modal.hide();
      this._modal = null;
    }
  }

}
