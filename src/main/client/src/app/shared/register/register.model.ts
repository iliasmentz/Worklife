import {FormGroup} from "@angular/forms";

export class Register {
  email: string;
  name: string;
  surname: string;
  username: string;
  password: string;
  password2: string;
  birthdate: Date;
  phone: string;
  address: string;

  constructor(form: FormGroup) {
    this.email = form.get('email').value;
    this.name = form.get('name').value;
    this.surname = form.get('surname').value;
    this.username = form.get('username').value;
    this.password = form.get('password').value;
    this.password2 = form.get('password2').value;
    this.birthdate = form.get('birthdate').value;
    this.phone = form.get('phone').value;
    this.address = form.get('address').value;
  }

}
