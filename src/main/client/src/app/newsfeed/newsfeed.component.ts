import {Component, OnInit} from '@angular/core';
import {User} from "../shared/user/user.model";

@Component({
  selector: 'app-newsfeed',
  templateUrl: './newsfeed.component.html',
  styleUrls: ['./newsfeed.component.css']
})
export class NewsfeedComponent implements OnInit {

  defaultPhoto = '/assets/img/user.svg';
  currentUser: User;
  constructor() { }

  ngOnInit() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

}
