import {FormGroup} from "@angular/forms";

export class JobDto {
  title: string;
  company: string;
  description: string;

  constructor() {
  }

  load(form: FormGroup) {
    this.title = form.get('title').value;
    this.company = form.get('company').value;
    this.description = form.get('description').value;
  }
}
