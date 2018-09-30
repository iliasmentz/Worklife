import {Component, OnInit} from '@angular/core';
import {BsModalRef} from "ngx-bootstrap";
import {Users} from "../../shared/user/user.model";
import {UserService} from "../../shared/user/user.service";

@Component({
  selector: 'app-search-user',
  templateUrl: './search-user.component.html',
  styleUrls: ['./search-user.component.css']
})
export class SearchUserComponent implements OnInit {
  users: Users;

  constructor(public _modal: BsModalRef,
              private _userService: UserService) {
  }

  ngOnInit() {
    this.users = [];
  }

  searchUser(event) {
    let term = event.target.value;
    if (term == '') {
      this.users = [];
      return;
    }
    this._userService.search(term)
      .then((users: Users) => {
        this.users = users;
      });
  }

  close() {
    if (this._modal) {
      this._modal.hide();
      this._modal = null;
    }
  }
}
