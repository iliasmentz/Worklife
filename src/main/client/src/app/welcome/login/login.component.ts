import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../shared/auth/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private _fb: FormBuilder, private authService: AuthService) {
  }

  ngOnInit() {
    this.loginForm = this._fb.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    });

  }

  onSubmit() {
    if (this.loginForm.valid) {
      const username = this.loginForm.get('username');
      const password = this.loginForm.get('password');
      this.authService.signinUser(username, password)
    }

    console.log(this.loginForm);
  }
}
