import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../shared/user/user.model";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  defaultPhoto = '/assets/img/user.svg';
  username: string;

  constructor(private router: Router) { }

  ngOnInit() {
    let user: User = JSON.parse(localStorage.getItem('currentUser'));
    this.username = user.username;
  }

  logout() {
    this.router.navigate(['/welcome']);
    localStorage.removeItem('access_token');
    // localStorage.removeItem('currentUser');
  }
}
