import { Component, OnInit } from '@angular/core';
import {User} from "../shared/user/user.model";
import {ActivatedRoute, Params} from "@angular/router";
import {UserService} from "../shared/user/user.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./css/bootstrap.css', './css/font-awesome.css', './css/theme.css']
})
export class ProfileComponent implements OnInit {
  user= JSON.parse(localStorage.getItem('currentUser'));
  username: string;
  constructor(private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit() {
    this.route.params.subscribe(
    (params: Params) => {
      this.username = params['username'];
      console.log(this.username);
      if(this.username === null) {
        this.user = JSON.parse(localStorage.getItem('currentUser'));
      } else {
        this.userService.getUserProfile(this.username)
          .then(profileUser => {
            this.user = profileUser;
          })
          .catch(err => {
            console.log("user does not exist " + this.username);
          })
      }
    }
    );
  }

}
