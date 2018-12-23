import {Component, OnInit} from '@angular/core';
import {Likes} from "../../../shared/likes/like.model";
import {BsModalRef} from "ngx-bootstrap";

@Component({
  selector: 'app-like',
  templateUrl: './like.component.html',
  styleUrls: ['./like.component.css']
})
export class LikeComponent implements OnInit {
  likes: Likes;

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
