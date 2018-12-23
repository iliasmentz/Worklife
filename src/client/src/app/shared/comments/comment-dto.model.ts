import {FormGroup} from "@angular/forms";

export class CommentDto {
  postId: number;
  context: string;

  constructor() {
  }

  public load(postId: number, form: FormGroup) {
    this.postId = postId
    this.context = form.get('content').value;
  }

}
