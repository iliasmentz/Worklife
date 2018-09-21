import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['../css/bootstrap.css', '../css/font-awesome.css', '../css/theme.css']
})
export class FriendsComponent implements OnInit {
  @Input() userId: number;

  constructor() { }

  ngOnInit() {
  }

}
