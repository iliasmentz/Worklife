import {FormGroup} from "@angular/forms";

export class SkillDto {
  name: string;
  level: number;
  visible: string;

  constructor() {}

  public load(form: FormGroup) {
    this.name = form.get('name').value;
    this.level = form.get('level').value;
    this.visible = form.get('visible').value;
  }
}

