import {Injectable} from '@angular/core';
import {RepoService} from "../repo/repo.service";
import {Login} from "./login.model";
import {map} from "rxjs/operators";
import {LoginCredentials} from "./login-credentials.model";

@Injectable()
export class AuthService {

  constructor(private repoService: RepoService) {
  }


  public get loggedIn(): boolean {
    return localStorage.getItem( 'access_token') !== null;
  }

  logout() {
    localStorage.removeItem('access_token');
  }

  loginUser(username: string, password: string) {
    const creds = new LoginCredentials(username, password);
    return this.repoService.login(creds)
      .pipe(map(login => this.deserializeLogin(login)))
      .toPromise() as Promise<Login>;
  }

  deserializeLogin(resp): Login {
    return new Login(resp);
  }


}
