import {Component, Input, OnInit} from '@angular/core';
import {Users} from "../../shared/user/user.model";

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['../css/bootstrap.css', '../css/font-awesome.css', '../css/theme.css']
})
export class FriendsComponent implements OnInit {
  @Input() userId: number;
  @Input() friends: Users;

  constructor() {
  }

  ngOnInit() {
  }

}
