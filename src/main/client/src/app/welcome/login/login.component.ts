import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../shared/user/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  showError: boolean;
  constructor(private _fb: FormBuilder,
              private userService: UserService) {
  }

  ngOnInit() {
    this.showError = false;

    this.loginForm = this._fb.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    });

  }

  onSubmit() {
    let invalid = false;
    if (this.loginForm.valid) {
      const username = this.loginForm.get('username').value;
      const password = this.loginForm.get('password').value;
      invalid = this.userService.loginUser(username, password);
    }
    if (!this.loginForm.valid || invalid) {
      this.showError = true
    }
  }

}
