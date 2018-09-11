import {Injectable} from '@angular/core';
import {map} from "rxjs/operators";
import {User} from "./user.model";
import {RepoService} from "../repo/repo.service";

@Injectable()
export class UserService {

  constructor(private repoService: RepoService) { }

  getUserProfile() {
    return this.repoService.get('profile/')
      .pipe(map(user => this.deserializeUser(user)))
      .toPromise() as Promise<User>;
  }

  deserializeUser(user): User {
    return new User(user);
  }
}
