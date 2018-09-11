import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../shared/auth/auth.service";
import {UserService} from "../../shared/user/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private _fb: FormBuilder, private router: Router,
              private authService: AuthService,
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
      this.authService.loginUser(username, password)
        .then(loginResponse => {
          console.log(loginResponse);
          localStorage.setItem('access_token', loginResponse.access_token);
          this.userService.getUserProfile()
            .then( currentUser => {
              localStorage.setItem('currentUser', JSON.stringify(currentUser))
              this.router.navigate(['']);
            })
            .catch( err => {
              console.log("can't get the user: " + err);
            })
        })
        .catch(err => {
          console.log(err);
          alert("Invalid credentials, please try again!");
        });
    }
  }

}
