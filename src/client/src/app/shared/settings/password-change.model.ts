import {FormGroup} from "@angular/forms";

export class PasswordChange {
  oldPassword: string;
  newPassword: string;
  newPasswordRepeat: string;

  constructor(form: FormGroup) {
    this.oldPassword = form.get('oldPassword').value;
    this.newPassword = form.get('newPassword').value;
    this.newPasswordRepeat = form.get('newPasswordRepeat').value;
  }
}
