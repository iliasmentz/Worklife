import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from 'rxjs';
import {LoginCredentials} from "../auth/login-credentials.model";

@Injectable()
export class RepoService {

  private API_ENDPOINT = 'http://localhost:8080/';
  private API_PREFIX = 'api/';

  constructor(private _http: HttpClient) {
  }

  get(url: string): Observable<Object> {
    return this._http
      .get(`${this.API_ENDPOINT}${this.API_PREFIX}${url}`)
  }

  post(url: string, body: any = null) {
    let headers = new HttpHeaders();
    return this._http
      .post(`${this.API_ENDPOINT}${this.API_PREFIX}${url}`, body, {headers: headers})
  }

  put(url: string, body: any = null): Observable<Object> {
    return this._http
      .put(`${this.API_ENDPOINT}${this.API_PREFIX}${url}`, body)
  }


  delete(url: string) {
    return this._http
      .delete(`${this.API_ENDPOINT}${this.API_PREFIX}${url}`)
  }

  public login(creds: LoginCredentials) {
    let data = {
      "username": creds.username,
      "password": creds.password,
      "grant_type": "password"
    };
    const headers = new HttpHeaders();
    headers.append("Content-Type", "application/x-www-form-urlencoded");


    return this._http.post(this.API_ENDPOINT + "oauth/token?username=" + data.username +
      "&password=" + data.password +
      "&grant_type=" + data.grant_type,
      "", {headers: headers});

  }
}
