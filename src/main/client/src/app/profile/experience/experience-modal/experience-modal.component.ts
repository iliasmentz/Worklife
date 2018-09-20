import {Component, OnInit} from '@angular/core';
import {BsModalRef} from 'ngx-bootstrap';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Experience} from "../../../shared/experience/experience.model";
import {ExperienceService} from "../experience.service";
import {ExperienceDto} from "../../../shared/experience/experience-dto.model";

@Component({
  selector: 'app-experience-modal',
  templateUrl: './experience-modal.component.html'
})
export class ExperienceModalComponent implements OnInit {

  mode: string;
  experience: Experience;
  experienceForm: FormGroup;
  present: boolean;

  constructor(public _modal: BsModalRef, private _fb: FormBuilder,
              private experienceService: ExperienceService) {
  }

  close() {
    if (this._modal) {
      this._modal.hide();
      this._modal = null;
    }
  }

  ngOnInit() {
    if (this.mode === 'Update') {
      this.experienceForm = this._editForm();
    } else {
      this.experienceForm = this._addForm();
    }
  }


  private _editForm = () => {
    this.present = (this.experience.endDate == null);
    return this._fb.group({
      title: [this.experience.title, Validators.required],
      company: [this.experience.company, Validators.required],
      startDate: [this.experience.startDate, Validators.required],
      endDate: [this.experience.endDate, Validators.required],
      visible: [this.experience.visible, Validators.required],
    });
  }

  private _addForm = () => {
    return this._fb.group({
      title: [null, Validators.required],
      company: [null, Validators.required],
      startDate: [null, Validators.required],
      endDate: [null],
      visible: [null, Validators.required],
    });
  }

  onSubmit(form: FormGroup) {
    let experienceRequest = new ExperienceDto();
    experienceRequest.load(this.present, form);
    if (this.mode === 'Update') {
      this.experienceService.updateExperience(this.experience.experienceId, experienceRequest)
        .then(experience => {
          this.experience = experience;
          this.experienceService.experience.next(experience);
        });
    } else {
      this.experienceService.addExperience(experienceRequest)
        .then(experience => {
          this.experience = experience;
          this.experienceService.experience.next(experience);
        });
    }
    this._modal.hide();
    this._modal = null;
  }

}
