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

  constructor(private _fb: FormBuilder,
              private userService: UserService) { }

  ngOnInit() {
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
      console.log("STELNW" + this.registerForm.valid);
      let registerRequest = new Register(this.registerForm);
      this.userService.register(registerRequest)
        .then(response => {
          console.log(response);

          this.userService.loginUser(registerRequest.username, registerRequest.password);
        })
        .catch(err => console.log(err));
    }
  }
}
