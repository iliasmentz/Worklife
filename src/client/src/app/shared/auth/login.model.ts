export class Login {
  access_token: string;
  token_type: string;
  expires_in: number;
  scope: string;

  constructor(obj: Login) {
    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }

}
