import {Component, OnInit} from '@angular/core';
import {BsModalRef} from "ngx-bootstrap";
import {Comments} from "../../../shared/comments/comment.model";

@Component({
  selector: 'app-like',
  templateUrl: './post-comments.component.html',
  styleUrls: ['./post-comments.component.css']
})
export class PostCommentComponent implements OnInit {
  comments: Comments;

  constructor(public _modal: BsModalRef) {
  }

  ngOnInit() {
  }

  close() {
    if (this._modal) {
      this._modal.hide();
      this._modal = null;
    }
  }


}
