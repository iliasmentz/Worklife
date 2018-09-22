import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BsModalRef} from "ngx-bootstrap";
import {JobService} from "../../shared/job/job.service";
import {Job} from "../../shared/job/job.model";
import {JobDto} from "../../shared/job/job-dto.model";

@Component({
  selector: 'app-job-modal',
  templateUrl: './job-modal.component.html',
  styleUrls: ['./job-modal.component.css']
})
export class JobModalComponent implements OnInit {


  mode: string;
  job: Job;
  jobForm: FormGroup;

  constructor(public _modal: BsModalRef, private _fb: FormBuilder,
              private jobService: JobService) {
  }

  close() {
    if (this._modal) {
      this._modal.hide();
      this._modal = null;
    }
  }

  ngOnInit() {
    if (this.mode === 'Update') {
      this.jobForm = this._editForm();
    } else {
      this.jobForm = this._addForm();
    }
  }


  private _editForm = () => {
    return this._fb.group({
      title: [this.job.title, Validators.required],
      company: [this.job.company, Validators.required],
      description: [this.job.description, Validators.required],
    });
  }

  private _addForm = () => {
    return this._fb.group({
      title: [null, Validators.required],
      company: [null, Validators.required],
      description: [null, Validators.required],
    });
  }

  onSubmit(form: FormGroup) {
    let jobRequest = new JobDto();
    jobRequest.load(form);

    if (this.mode === 'Update') {
      this.jobService.updateJob(this.job.id, jobRequest)
        .then(job => {
          this.job = job;
          this.jobService.job.next(job);
        });
    } else {
      this.jobService.addJob(jobRequest)
        .then(job => {
          this.job = job;
          this.jobService.job.next(job);
        });
    }
    this._modal.hide();
    this._modal = null;
  }
}
