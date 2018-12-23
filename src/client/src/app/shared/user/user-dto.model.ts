import {FormGroup} from "@angular/forms";

export class UserDto {
  address: string;
  birthdate: Date;
  email: string;
  name: string;
  surname: string;
  phoneNumber: string;
  dateCreated: Date;

  constructor(form: FormGroup) {
    this.email = form.get('email').value;
    this.name = form.get('name').value;
    this.surname = form.get('surname').value;
    this.birthdate = form.get('birthdate').value;
    this.phoneNumber = form.get('phoneNumber').value;
    this.address = form.get('address').value;
  }
}
