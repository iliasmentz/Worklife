import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from './auth.service';
import {map} from "rxjs/operators";

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService,
              private router: Router) {
  }


  canActivate(next: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): Observable<boolean> {
    return this.authService.isLoggedIn
      .pipe(map((isLoggedIn: boolean) => {
        if (!isLoggedIn) {
          this.router.navigate(['/welcome']);
          return false;
        }
        return true;
      }));
  }
}
