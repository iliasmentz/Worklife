import {Component, OnInit} from '@angular/core';
import {BsModalRef} from "ngx-bootstrap";
import {Users} from "../../shared/user/user.model";

@Component({
  selector: 'app-applicants',
  templateUrl: './applicants.component.html',
  styleUrls: ['./applicants.component.css']
})
export class ApplicantsComponent implements OnInit {
  users: Users;

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
