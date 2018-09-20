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

  constructor(private _fb: FormBuilder,
              private userService: UserService) {
  }

  ngOnInit() {
    this.loginForm = this._fb.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    });

  }

  onSubmit() {
    if (this.loginForm.valid) {
      const username = this.loginForm.get('username').value;
      const password = this.loginForm.get('password').value;
      this.userService.loginUser(username, password);
    }
  }

}
