import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../shared/user/user.model";

@Component({
  selector: 'app-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['../css/bootstrap.css', '../css/font-awesome.css', '../css/theme.css']
})
export class BasicInfoComponent implements OnInit {

  @Input() user: User;

  constructor() { }

  ngOnInit() {
  }

}
