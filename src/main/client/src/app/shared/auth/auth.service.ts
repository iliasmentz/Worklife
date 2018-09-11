import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable()
export class AuthService {

  private _loggedIn = new BehaviorSubject<boolean>(false);

  get isLoggedIn(): Observable<boolean> {
    return this._loggedIn;
  }

  // constructor(private _i: Injector,
  //             private _http: HttpClient,
  //             private cookieService: CookieService,
  //             private info: InfoService,
  //             private bgAdminUserService: BgAdminUserService,
  //             private gAuthService: GoogleAuthService) {
  // }
  //
  // /**
  //  * Checks if the stored JWT authentication token has expired.
  //  *
  //  * @returns {boolean} - true if expired otherwise false
  //  */
  // isAuthenticated() {
  //
  //   const jwtHelper = new JwtHelperService();
  //
  //   const token = this.cookieService.get(AUTHORIZATION_COOKIE_NAME);
  //
  //   return !!token && !jwtHelper.isTokenExpired(token);
  // }
  //
  // initLogin(): Promise<void> {
  //   return this.bgAdminUserService
  //     .get()
  //     .then(resp => this.successLogin(resp['data'] as BgAdminUser))
  //     .catch(err => this.logout());
  // }

  //
  // /**
  //  * Attempts to authenticate the user with the backend.
  //  *
  //  * @param {UserCredentials} user - the user credentials
  //  */
  // login(user: UserCredentials) {
  //
  //   this._http.post(
  //     `${environment.adminApi}users/signin`,
  //     user,
  //     {observe: 'response', withCredentials: true})
  //     .subscribe(res => {
  //
  //         // get JWT auth token from the response headers
  //         const authToken: string = res.headers.get('authorization');
  //
  //         // decode the token to get its payload
  //         const tokenPayload: JwtPayload = jwt_decode(authToken) as JwtPayload;
  //
  //         // store cookie
  //         this.cookieService.set(AUTHORIZATION_COOKIE_NAME, authToken, new Date(tokenPayload.exp * 1000));
  //
  //         this.successLogin(res.body['data'] as BgAdminUser);
  //         this._i.get(Router).navigate(['/admin/home']);
  //       },
  //       err => {
  //         if (err.status === 401 && err.error.error.code === 801) {
  //           this.info.warning('Your user name or password is incorrect.', 'Bad Credentials');
  //         }
  //       });
  // }
  //
  // logout() {
  //   this.bgAdminUserService.setBgAdminUser(null);
  //   this._loggedIn.next(false);
  //   this._i.get(Router).navigate(['/login']);
  // }
  //
  // private successLogin(user: BgAdminUser) {
  //   const newUser = null;//new BgAdminUser(user.username, user.firstName, user.lastName, user.email, user.photoUrl);
  //   this.bgAdminUserService.setBgAdminUser(newUser);
  //   this._loggedIn.next(true);
  // }

}
