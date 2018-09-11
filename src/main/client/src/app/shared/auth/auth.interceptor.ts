import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';

import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  client_id = 'my-trusted-client';
  client_secret = 'secret';

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authorization;
    if (localStorage.getItem('access_token') !== null) {
      authorization = `Bearer ` +localStorage.getItem('access_token');
    } else {
      authorization = `Basic ` + btoa(this.client_id + ":" + this.client_secret);
    }

    req = req.clone({
      setHeaders: {
        'Content-Type' : 'application/json; charset=utf-8',
        'Accept'       : 'application/json',
        'Authorization': authorization,
      },
    });


    return next.handle(req);
  }
}
