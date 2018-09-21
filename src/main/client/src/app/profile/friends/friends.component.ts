import {Component, Input, OnInit} from '@angular/core';
import {Users} from "../../shared/user/user.model";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../shared/user/user.service";

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['../css/bootstrap.css', '../css/font-awesome.css', '../css/theme.css']
})
export class FriendsComponent implements OnInit {
  @Input() userId: number;
  friends: Users;

  constructor(private route: ActivatedRoute,
              private userService: UserService) {
  }

  ngOnInit() {
    this.route.params.subscribe(() => {
      this.userService.getFriends(this.userId)
        .then((users: Users) => {
          this.friends = users.slice(0, 5);
        })
    });
  }

}
