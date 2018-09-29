import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PasswordChange} from "../shared/settings/password-change.model";
import {EmailChange} from "../shared/settings/email-change.model";
import {SettingsService} from "../shared/settings/settings.service";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  passwordForm: FormGroup;
  emailForm: FormGroup;
  passwordChange: boolean;
  emailChange: boolean;
  emailInvalid: boolean;
  passwordMismatch: boolean;

  constructor(private _fb: FormBuilder,
              private _settingsService: SettingsService) {
  }


  ngOnInit() {
    this.passwordForm = this._initPasswordForm();
    this.emailForm = this._initEmailForm();
    this.passwordChange = false;
    this.emailChange = false;
    this.emailInvalid = false;
    this.passwordMismatch = false;
  }

  private _initEmailForm = () => {
    return this._fb.group({
      newEmail: [null, Validators.required]
    });
  }

  private _initPasswordForm = () => {
    return this._fb.group({
      oldPassword: [null, Validators.required],
      newPassword: [null, Validators.required],
      newPasswordRepeat: [null, Validators.required],
    });
  }


  onChangePassword(passwordForm: FormGroup) {
    let password = new PasswordChange(passwordForm);
    if (passwordForm.valid || password.newPassword !== password.newPasswordRepeat) {
      this.passwordMismatch = true;
      return;
    }

    this._settingsService.updatePassword(password);
    this.passwordChange = true;
    this.passwordMismatch = false;
    this.passwordForm = this._initPasswordForm();
  }

  onChangeEmail(emailForm: FormGroup) {
    let email = new EmailChange(emailForm);
    if (emailForm.valid) {
      this.emailInvalid = true;
      return;
    }
    this._settingsService.updateEmail(email);
    this.emailChange = true;
    this.emailInvalid = false;
    this.emailForm = this._initEmailForm();
  }
}
