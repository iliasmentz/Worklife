import {FormGroup} from "@angular/forms";

export class ExperienceDto {
  title: string;
  company: string;
  startDate: Date;
  endDate: Date;
  visible: number;

  constructor() {
  };

  public load(present: boolean, form: FormGroup) {
    this.title = form.get('title').value;
    this.company = form.get('company').value;
    this.startDate = form.get('startDate').value;
    this.endDate = present ? null : form.get('endDate').value;
    this.visible = form.get('visible').value;
  }
}
