import {Component, OnInit} from '@angular/core';
import {BsModalRef} from 'ngx-bootstrap';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Education} from "../../../shared/education/education.model";
import {EducationService} from "../education.service";
import {EducationDto} from "../../../shared/education/education-dto.model";

@Component({
  selector: 'app-education-modal',
  templateUrl: './education-modal.component.html'
})
export class EducationModalComponent implements OnInit {

  mode: string;
  education: Education;
  educationForm: FormGroup;
  present: boolean;

  constructor(public _modal: BsModalRef, private _fb: FormBuilder,
              private educationService: EducationService) {
  }

  close() {
    if (this._modal) {
      this._modal.hide();
      this._modal = null;
    }
  }

  ngOnInit() {
    if (this.mode === 'Update') {
      this.educationForm = this._editForm();
    } else {
      this.educationForm = this._addForm();
    }
  }


  private _editForm = () => {
    this.present = (this.education.endDate == null);
    return this._fb.group({
      universityDegree: [this.education.universityDegree, Validators.required],
      universityName: [this.education.universityName, Validators.required],
      startDate: [this.education.startDate, Validators.required],
      endDate: [this.education.endDate, Validators.required],
      visible: [this.education.visible, Validators.required],
    });
  }

  private _addForm = () => {
    return this._fb.group({
      universityDegree: [null, Validators.required],
      universityName: [null, Validators.required],
      startDate: [null, Validators.required],
      endDate: [null],
      visible: [null, Validators.required],
    });
  }

  onSubmit(form: FormGroup) {
    let educationRequest = new EducationDto();
    educationRequest.load(this.present, form);
    if (this.mode === 'Update') {
      this.educationService.updateEducation(this.education.educationId, educationRequest)
        .then(education => {
          this.education = education;
          this.educationService.education.next(education);
        });
    } else {
      this.educationService.addEducation(educationRequest)
        .then(education => {
          this.education = education;
          this.educationService.education.next(education);
        });
    }
    this._modal.hide();
    this._modal = null;
  }

}
