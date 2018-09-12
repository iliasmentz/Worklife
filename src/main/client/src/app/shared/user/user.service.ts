import {Injectable} from '@angular/core';
import {map} from "rxjs/operators";
import {User} from "./user.model";
import {RepoService} from "../repo/repo.service";
import {AuthService} from "../auth/auth.service";
import {Router} from "@angular/router";
import {Register} from "../register/register.model";

@Injectable()
export class UserService {

  constructor(private repoService: RepoService, private authService: AuthService, private router: Router) { }

  getUserProfile() {
    return this.repoService.get('profile/')
      .pipe(map(user => this.deserializeUser(user)))
      .toPromise() as Promise<User>;
  }

  loginUser(username: string, password: string) {
    this.authService.loginUser(username, password)
      .then(loginResponse => {

        console.log(loginResponse);
        localStorage.setItem('access_token', loginResponse.access_token);

        this.getUserProfile()
          .then(currentUser => {
            localStorage.setItem('currentUser', JSON.stringify(currentUser));
            this.router.navigate(['']);
          })
          .catch( err => {
            console.log("can't get the user: " + err);
          })
      })
  }

  register(register: Register) {
    return this.repoService.post("auth/register", register)
      .toPromise();
  }

  deserializeUser(user): User {
    return new User(user);
  }
}
