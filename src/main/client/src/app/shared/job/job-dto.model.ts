import {FormArray, FormGroup} from "@angular/forms";

export class JobDto {
  title: string;
  company: string;
  description: string;
  skills: string[];

  constructor() {
  }

  load(form: FormGroup) {
    this.title = form.get('title').value;
    this.company = form.get('company').value;
    this.description = form.get('description').value;
    this.skills = [];
    let skillControls = (<FormArray> form.get('skills')).controls;
    skillControls.forEach((skillControl) => {
      this.skills.push(skillControl.get('skill').value);
    });
  }
}
