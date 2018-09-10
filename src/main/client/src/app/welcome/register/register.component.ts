import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, NgForm, Validators} from "@angular/forms";
import {AuthService} from "../../shared/auth/auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;

  constructor(private _fb: FormBuilder, private authService: AuthService  ) { }

  ngOnInit() {
    this.registerForm = this._fb.group({
      email: [null, Validators.email],
      name: [null, Validators.min(3)],
      surname: [null, Validators.min(2)],
      username: [null, Validators.min(3)],
      password: [null, Validators.min(5)],
      password2: [null, Validators.min(5)],
      phone: [null],
      address: [null]
    });
  }

  onSubmit(form: NgForm) {
    if (this.registerForm.valid) {

    }
    console.log(form);
  }
}
