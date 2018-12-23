import {FormGroup} from "@angular/forms";

export class PostDto {
  context: string;
  visible: string;

  constructor() {
  }

  public load(form: FormGroup) {
    this.context = form.get('context').value;
    this.visible = form.get('visible').value;
  }
}
