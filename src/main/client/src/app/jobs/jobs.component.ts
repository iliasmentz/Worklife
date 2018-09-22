import {Component, OnInit} from '@angular/core';
import {JobService} from "../shared/job/job.service";
import {Jobs} from "../shared/job/job.model";
import {BsModalService, ModalOptions} from "ngx-bootstrap";
import {JobModalComponent} from "./job-modal/job-modal.component";

const options: ModalOptions = {
  class: 'modal-md',
  backdrop: 'static',
};

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.css']
})
export class JobsComponent implements OnInit {
  jobs: Jobs;
  color = '#B3C9D5';

  constructor(private _jobService: JobService,
              private _modal: BsModalService) {
  }

  ngOnInit() {
    this._jobService.getJobs()
      .then((jobs: Jobs) => {
        this.jobs = jobs;
      })
  }

  addJob() {
    const initialState = {
      mode: 'Add',
      job: null
    };

    this._modal.show(JobModalComponent, {...options, initialState});
  }
}
