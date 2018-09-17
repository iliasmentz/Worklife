import {FormGroup} from "@angular/forms";

export class EducationDto {
  universityDegree: string;
  universityName: string;
  startDate: Date;
  endDate: Date;
  visible: number;

  constructor() {
  }

  load(present: boolean, form: FormGroup) {
    this.universityDegree = form.get('universityDegree').value;
    this.universityName = form.get('universityName').value;
    this.startDate = form.get('startDate').value;
    this.endDate = present ? null : form.get('endDate').value;
    this.visible = form.get('visible').value;
  }
}

