import {Component, OnInit} from '@angular/core';
import {JobService} from "../shared/job/job.service";
import {Job, Jobs} from "../shared/job/job.model";
import {BsModalService, ModalOptions} from "ngx-bootstrap";
import {JobModalComponent} from "./job-modal/job-modal.component";
import {JobApplication, JobApplications} from "../shared/job/job-application.model";
import {User, Users} from "../shared/user/user.model";
import {ApplicantsComponent} from "./applicants/applicants.component";

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
  jobApplications: JobApplications = [];
  color = '#B3C9D5';

  constructor(private _jobService: JobService,
              private _modal: BsModalService) {
  }

  ngOnInit() {
    this._jobService.getJobs()
      .then((jobs: Jobs) => {
        this.jobs = jobs;
      });
    this._jobService.getJobApplications()
      .then((jobsApplies: JobApplications) => {
        this.jobApplications = jobsApplies;
      });
    this._jobService.job.subscribe((newJob: Job) => {
      let updateItem = this.jobs.find(x => x.id === newJob.id);
      if (updateItem != null) {
        let index = this.jobs.indexOf(updateItem);
        this.jobs[index] = newJob;
      } else {
        this.jobs.push(newJob);
      }
    });
  }

  myJob(job: Job): boolean {
    let user: User = JSON.parse(localStorage.getItem('currentUser'));
    return job.author.userId === user.userId;
  }

  addJob() {
    const initialState = {
      mode: 'Add',
      job: null
    };

    this._modal.show(JobModalComponent, {...options, initialState});
  }

  jobApply(id: number) {
    this._jobService.applyJob(id)
      .then((jobApplication: JobApplication) => {
        this.jobApplications.push(jobApplication);
      });
  }

  applied(id: number): boolean {
    let job = this.jobApplications.find(x => x.jobId === id);
    return job != null;
  }

  jobDelete(id: number) {
    this._jobService.deleteJob(id);
    this.jobs = this.jobs.filter(function (obj) {
      return obj.id !== id;
    });
  }

  jobEdit(job: Job) {
    const initialState = {
      mode: 'Update',
      job: job
    };

    this._modal.show(JobModalComponent, {...options, initialState});
  }

  jobApplicants(id: number) {
    this._jobService.getJobApplicants(id)
      .then((users: Users) => {

        const initialState = {
          mode: 'Update',
          users: users
        };

        this._modal.show(ApplicantsComponent, {...options, initialState});
      })
  }
}
