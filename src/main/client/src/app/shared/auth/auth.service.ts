import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {LoginCredentials} from "./login-credentials.model";
import {RepoService} from "../repo/repo.service";

@Injectable()
export class AuthService {

  constructor(private repoService: RepoService) {}

  private _loggedIn = new BehaviorSubject<boolean>(true);

  get isLoggedIn(): Observable<boolean> {
    return this._loggedIn;
  }

  signinUser(username: string, password: string) {
    const creds = new LoginCredentials(username, password);
    const prom = this.repoService.post('/oauth/token', creds)
      .toPromise() as Promise<Object>
    prom
      .then(
        response => console.log(response)
      )
      .catch(
        error => console.log(error)
      );
  }

  getToken() {

  }
}
