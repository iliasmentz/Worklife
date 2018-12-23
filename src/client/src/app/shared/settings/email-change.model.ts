import {FormGroup} from "@angular/forms";

export class EmailChange {
  newEmail: string;

  constructor(form: FormGroup) {
    this.newEmail = form.get('newEmail').value;
  }
}
