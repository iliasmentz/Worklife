import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../shared/user/user.model";
import {AuthService} from "../../shared/auth/auth.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  defaultPhoto = '/assets/img/user.svg';
  username: string;

  constructor(private router: Router, private authService: AuthService) {
  }

  ngOnInit() {
    let user: User = JSON.parse(localStorage.getItem('currentUser'));
    this.username = user.username;
  }

  logout() {
    this.router.navigate(['/welcome']);
    this.authService.logout();
    // localStorage.removeItem('currentUser');
  }
}
