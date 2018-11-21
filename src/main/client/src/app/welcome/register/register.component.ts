import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Register} from "../../shared/register/register.model";
import {UserService} from "../../shared/user/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  showError: boolean;
  showPasswordMismatch: boolean;
  errorMessage: string;

  constructor(private _fb: FormBuilder,
              private userService: UserService) { }

  ngOnInit() {
    this.showError = false;
    this.showPasswordMismatch = false;
    this.registerForm = this._fb.group({
      email: [null, [Validators.required, Validators.email]],
      name: [null, [Validators.required, Validators.minLength(3)]],
      surname: [null, [Validators.required, Validators.minLength(2)]],
      username: [null, [Validators.required, Validators.minLength(3)]],
      birthdate: [null, Validators.required],
      password: [null, [Validators.required, Validators.minLength(6)]],
      password2: [null, [Validators.required, Validators.minLength(6)]],
      phone: [null],
      address: [null]
    });
  }

  onSubmit() {
    if (this.registerForm.valid) {
      this.showPasswordMismatch = false;
      let registerRequest = new Register(this.registerForm);
      this.userService.register(registerRequest)
        .then(() => {
          console.log(registerRequest)
          this.showError = false;
          this.userService.loginUser(registerRequest.username, registerRequest.password)
            .then().catch();
        })
        .catch(err => {
          console.log(err);
          this.showError = true;
          this.errorMessage = err.error;
        });
    } else {
      this.showPasswordMismatch = true;
    }
  }
}
