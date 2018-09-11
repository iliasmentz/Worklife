import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {RepoResponse} from './repo-response';

@Injectable()
export class RepoService {

  private API_ENDPOINT = '/api/';

  constructor(private _http: HttpClient) {
  }

  get(url: string): Observable<Object> {
    return this._http
      .get(`${this.API_ENDPOINT}${url}`)
      .pipe(map((resp: RepoResponse) => resp.data));
  }

  post(url: string, body: any = null) {
    return this._http
      .post(`${this.API_ENDPOINT}${url}`, body)
      .pipe(map((resp: RepoResponse) => resp.data));
  }

  put(url: string, body: any = null) {
    return this._http
      .put(`${this.API_ENDPOINT}${url}`, body)
      .pipe(map((resp: RepoResponse) => resp.data));
  }

  patch(url: string, body: any = null) {
    return this._http
      .patch(`${this.API_ENDPOINT}${url}`, body)
      .pipe(map((resp: RepoResponse) => resp.data));
  }

  delete(url: string) {
    return this._http
      .delete(`${this.API_ENDPOINT}${url}`)
      .pipe(map((resp: RepoResponse) => resp.data));
  }
}
