import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../shared/user/user.model";
import {AuthService} from "../../shared/auth/auth.service";
import {FileUploadService} from "../../shared/fiile-upload/file-upload.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  user: User;
  username: string;

  constructor(private router: Router,
              private _uploadService: FileUploadService,
              private authService: AuthService) {
  }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.username = this.user.username;

    this._uploadService.imagePath.subscribe((newImagePath: string) => {
      this.user.imagePath = newImagePath
    });
  }

  logout() {
    this.router.navigate(['/welcome']);
    this.authService.logout();
    // localStorage.removeItem('currentUser');
  }
}
