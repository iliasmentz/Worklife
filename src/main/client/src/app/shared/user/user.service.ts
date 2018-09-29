import {Injectable} from '@angular/core';
import {map} from "rxjs/operators";
import {User, Users} from "./user.model";
import {RepoService} from "../repo/repo.service";
import {AuthService} from "../auth/auth.service";
import {Router} from "@angular/router";
import {Register} from "../register/register.model";
import {UserDto} from "./user-dto.model";
import {Subject} from "rxjs/Subject";

@Injectable()
export class UserService {
  user = new Subject();

  constructor(private repoService: RepoService, private authService: AuthService, private router: Router) { }

  getUser() {
    return this.repoService.get('profile/')
      .pipe(map(user => this.deserializeUser(user)))
      .toPromise() as Promise<User>;
  }

  getUserProfile(username: string) {
    return this.repoService.get('profile/' + username)
      .pipe(map(user => this.deserializeUser(user)))
      .toPromise() as Promise<User>;
  }

  loginUser(username: string, password: string) {
    this.authService.loginUser(username, password)
      .then(loginResponse => {

        localStorage.setItem('access_token', loginResponse.access_token);

        this.getUser()
          .then(currentUser => {
            localStorage.setItem('currentUser', JSON.stringify(currentUser));
            this.router.navigate(['']);
          })
          .catch( err => {
            console.log("can't get the user: " + err);
          })
      })
  }

  updateUser(userRequest: UserDto) {
    return this.repoService.put("profile/", userRequest)
      .pipe(map(user => this.deserializeUser(user)))
      .toPromise() as Promise<User>;
  }

  register(register: Register) {
    return this.repoService.post("auth/register", register)
      .toPromise();
  }


  getFriends(userId: number) {
    return this.repoService.get('network/connections/users/' + userId)
      .pipe(map((users: Users) => {
        return users.map(user => this.deserializeUser(user))
      }))
      .toPromise() as Promise<Users>;
  }

  deserializeUser(user): User {
    return new User(user);
  }

  getAllUsers() {
    return this.repoService.get('auth/users/')
      .pipe(map((users: Users) => {
        return users.map(user => this.deserializeUser(user))
      }))
      .toPromise() as Promise<Users>;
  }
}
