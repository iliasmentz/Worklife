
export class LoginCredentials {
  grant_type = 'password';
  client_id = 'my-trusted-client';
  client_secret = 'secret';
  username: string;
  password: string;

  constructor (username: string, password: string) {
    this.username = username;
    this.password = password;
  }
}
